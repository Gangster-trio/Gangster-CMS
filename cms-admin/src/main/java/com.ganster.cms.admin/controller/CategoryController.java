package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.PermissionNotFoundException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
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
    UserService userService;

    private Integer siteid;

    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 1;
        }
        siteid = siteId;
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        CategoryExample categoryExample = new CategoryExample();
        List<Integer> categoryIdList = PermissionUtil.getAllPermittedCategory(userId, siteId, CmsConst.PERMISSION_READ);
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

        //Determined whether current category article,if true,delete article and category
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Article> list = articleService.selectArticleByCategoryId(id);
        if (!list.isEmpty()) {
            for (Article article : list) {
                articleService.deleteByPrimaryKey(article.getArticleId());
            }
        }
        //删除作者对栏目的权限
        if (categoryService.deleteByPrimaryKey(id) == 1) {
            try {
                permissionService.deleteUserPermission(userId, siteid, id, CmsConst.PERMISSION_READ);
                permissionService.deleteUserPermission(userId, siteid, id, CmsConst.PERMISSION_WRITE);
                permissionService.deleteUserPermission(1, siteid, id, CmsConst.PERMISSION_READ);
                permissionService.deleteUserPermission(1, siteid, id, CmsConst.PERMISSION_WRITE);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                return new Message(1, "false", "用户未找到");
            } catch (GroupNotFountException e) {
                e.printStackTrace();
                return new Message(1, "false", "组找不见");
            } catch (PermissionNotFoundException e) {
                e.printStackTrace();
                return new Message(1, "false", "权限没有找到");
            }

            return super.buildMessage(0, "success", 1);
        } else {
            return super.buildMessage(1, "false", 0);
        }
    }

    @PostMapping("/update/{id}")
    public Message update(@PathVariable("id") Integer id, @RequestBody Category category) {
        category.setCategoryId(id);
        category.setCategoryUpdateTime(new Date());
        int count = categoryService.updateByPrimaryKeySelective(category);
        if (count == 1) return super.buildMessage(0, "success", 1);
        else return super.buildMessage(1, "false", 0);
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
        category.setCategoryCreateTime(new Date());
        category.setCategorySkin("default");
        Category parentCategory = categoryService.selectByPrimaryKey(category.getCategoryParentId());
        Integer siteId = parentCategory.getCategorySiteId();
        category.setCategorySiteId(siteId);
        Integer level = parentCategory.getCategoryLevel();
        if (level == -1) {
            category.setCategoryLevel(0);
        } else {
            category.setCategoryLevel(level + 1);
        }

        int count = categoryService.insert(category);

        try {
            User user = userService.selectByPrimaryKey(userId);
            permissionService.addCategoryPermissionToUser(userId, siteId, category.getCategoryId(), CmsConst.PERMISSION_READ);
            permissionService.addCategoryPermissionToUser(userId, siteId, category.getCategoryId(), CmsConst.PERMISSION_WRITE);
            if (!user.getUserName().equals("admin")) {
                permissionService.addCategoryPermissionToUser(1, siteId, category.getCategoryId(), CmsConst.PERMISSION_READ);
                permissionService.addCategoryPermissionToUser(1, siteId, category.getCategoryId(), CmsConst.PERMISSION_WRITE);
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
}