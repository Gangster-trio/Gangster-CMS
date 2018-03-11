package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.*;
import com.ganster.cms.core.util.PermissionUtil;
import com.ganster.cms.core.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;
    @Autowired
    private ModuleService moduleService;
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);


    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 1;
        }
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        CategoryExample categoryExample = new CategoryExample();
        List<Integer> categoryIdList = PermissionUtil.getAllPermittedCategory(userId, siteId, CmsConst.PERMISSION_READ);
        if (categoryIdList == null || categoryIdList.isEmpty()) {
            return super.buildAjaxData(2, "no privilege", 0, null);
        }
        categoryExample.or().andCategoryLevelNotEqualTo(-1).andCategoryIdIn(categoryIdList);
        PageInfo<Category> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
        List<Category> categories = pageInfo.getList();
        if (categories == null || categories.isEmpty()) {
            return super.buildAjaxData(0, "success", 0, null);
        } else {
            return super.buildAjaxData(0, "success", pageInfo.getTotal(), categories);
        }
    }

    @GetMapping("/select")
    public List<CategoryTree> select() {
        List<CategoryTree> treeList = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryLevelEqualTo(-1);
        List<Category> list = categoryService.selectByExample(categoryExample);
        if (list == null || list.isEmpty()) {
            Category category = new Category();
            category.setCategoryLevel(-1);
            category.setCategoryTitle("root");
            categoryService.insert(category);
            CategoryTree tree = new CategoryTree();
            tree.setId(1);
            tree.setName("root");
            tree.setChildren(null);
            treeList.add(tree);
            return treeList;
        } else {
            CategoryExample categoryExample1 = new CategoryExample();
            categoryExample1.or().andCategoryLevelEqualTo(-1);
            List<Category> list1 = categoryService.selectByExample(categoryExample1);
            Category category = list1.get(0);
            CategoryTree tree = categoryService.toTree(category);
            categoryExample.or().andCategoryParentIdEqualTo(1);
            List<Category> list2 = categoryService.selectByExample(categoryExample);
            if (list2.size() > 0) {
                for (Category c : list) {
                    treeList.add(categoryService.toTree(c));
                }
                tree.setChildren(treeList);
            }
            return treeList;
        }
    }

    @GetMapping("/delete/{id}")
    public Message delete(@PathVariable("id") Integer id) {
        int count = 0;
        //Determined whether current category article,if true,delete article and category
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        Category category = categoryService.selectByPrimaryKey(id);
        // 判断当前用户是否具有写的权限
        if (category == null) {
            return super.buildMessage(1, "no category", null);
        }
        if (!permissionService.hasCategoryPermission(userId, category.getCategorySiteId(), id, CmsConst.PERMISSION_WRITE)) {
            return super.buildMessage(2, "no permission", null);
        }
        // 得到当前栏目所属的网站
        Integer siteId = category.getCategorySiteId();
        //删除作者对栏目的权限
        count += categoryService.deleteCategoryInfo(siteId, id, CmsConst.PERMISSION_READ);
        count += categoryService.deleteCategoryInfo(siteId, id, CmsConst.PERMISSION_WRITE);
        // 删除该栏目下的所有文章
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(category.getCategoryId());
        List<Article> articleList = articleService.selectArticleByCategoryId(category.getCategoryId());
        for (Article article : articleList) {
            count += articleService.deleteArticleWithTags(article.getArticleId());
        }

        // 最后删除栏目
        PermissionUtil.flush(userId);
        return super.buildMessage(0, "success", count);
    }

    @PostMapping("/update/{id}")
    public Message update(@PathVariable("id") Integer id, @RequestBody Category category) {

        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");


        Category c = categoryService.selectByPrimaryKey(id);
        if (null == c) {
            return super.buildMessage(1, "no so category", null);
        }


        if (!permissionService.hasCategoryPermission(userId, c.getCategorySiteId(), id, CmsConst.PERMISSION_WRITE)) {
            return super.buildMessage(2, "no permision", null);
        }


        category.setCategoryId(id);
        category.setCategoryUpdateTime(new Date());
        try {
            int count = categoryService.updateByPrimaryKeySelective(category);
            if (count == 1) return super.buildMessage(0, "success", 1);
            else return super.buildMessage(1, "false", 0);
        } catch (Exception e) {
            LOGGER.error("update category error  " + e);
            return super.buildMessage(1, "false", null);
        }
    }

    @GetMapping("/details/{id}")
    public CategoryWithParent details(@PathVariable("id") Integer id) {
        Category category = categoryService.selectByPrimaryKey(id);
        if (category != null) return new CategoryWithParent(category.getCategoryTitle(), category);
        else return null;
    }

    @PostMapping("/add")
    public Message add(@RequestBody Category category) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");

        // 判断栏目内容是否为空
        if (category == null) {
            return super.buildMessage(1, "false", null);
        }
        // 判断登陆用户是否具有对栏目具有写的操作
        // 查询栏目对应的modelId
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.or().andModuleNameEqualTo("栏目管理");
        Module module = moduleService.selectByExample(moduleExample).get(0);
        if (!permissionService.hasModulePermission(userId, category.getCategorySiteId(), module.getModuleId(), CmsConst.PERMISSION_WRITE)) {
            return super.buildMessage(2, "Null Permission", null);
        }
        // 插入栏目数据表的操作
        category.setCategoryCreateTime(new Date());
        category.setCategorySkin("default");
        Category parentCategory = categoryService.selectByPrimaryKey(category.getCategoryParentId());
        Integer level = parentCategory.getCategoryLevel();
        if (level == -1) {
            category.setCategoryLevel(0);
        } else {
            category.setCategoryLevel(level + 1);
        }

        int count = categoryService.insert(category);
        // 插入权限表的操作
        try {
            User user = userService.selectByPrimaryKey(userId);
            permissionService.addCategoryPermissionToUser(userId, category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_READ);
            permissionService.addCategoryPermissionToUser(userId, category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_WRITE);
            if (!user.getUserName().equals("admin")) {
                UserExample userExample = new UserExample();
                userExample.or().andUserNameEqualTo("admin");
                User u = userService.selectByExample(userExample).get(0);
                permissionService.addCategoryPermissionToUser(u.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_READ);
                permissionService.addCategoryPermissionToUser(u.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_WRITE);
            }
            PermissionUtil.flush(userId);
        } catch (GroupNotFountException e) {
            e.printStackTrace();
            return new Message(1, "false", "组找不见");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new Message(1, "false", "没有找到该用户");
        }
        if (count == 1) return new Message(0, "success", count);
        else return new Message(1, "false", count);
    }

    @PostMapping("delete/batch")
    @ResponseBody
    public Message batchDelete(String categoryIdData) {
        if (StringUtil.isNullOrEmpty(categoryIdData)) {
            return super.buildMessage(1, "传入的栏目id信息为空", null);
        }
        int deleteBatchRead = categoryService.deleteBatchCategoryInfo(categoryIdData, CmsConst.PERMISSION_READ);
        int deleteBatchWrite = categoryService.deleteBatchCategoryInfo(categoryIdData, CmsConst.PERMISSION_WRITE);
        if (deleteBatchRead == 0 || deleteBatchWrite == 0) {
            return super.buildMessage(1, "删除发生错误", null);
        }
        return super.buildMessage(0, "删除成功", deleteBatchRead + deleteBatchWrite);
    }
}