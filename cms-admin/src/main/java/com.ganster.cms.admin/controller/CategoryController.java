package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.admin.util.PageUtil;
import com.ganster.cms.admin.util.UserUtil;
import com.ganster.cms.core.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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


    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(siteId);
        List<Category> list = categoryService.selectByExample(categoryExample);
        Integer userId = UserUtil.getCurrentUserId();
        if (!list.isEmpty()) {
            return PageUtil.getAjaxCateogryData(page, limit, categoryExample, categoryService, list);
        } else {
            return super.buildAjaxData(1, "no data", 0, null);
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
    public Message delete(@PathVariable("id") Integer id, ModelMap modelMap) {
        Category category = categoryService.selectByPrimaryKey(id);
        Integer userId = UserUtil.getCurrentUserId();


        //Determine whether current user permissions
        if (!(permissionService.hasCategoryPermission(userId, category.getCategorySiteId(), id, CmsConst.PERMISSION_DEL))) {
            return super.buildMessage(2, "no permisison", null);
        }


        //Detemermine whether current category article,if true,delete article and category
        List<Article> list = articleService.selectArticleByCategoryId(id);
        if (!list.isEmpty()) {
            for (Article article : list) {
                articleService.deleteByPrimaryKey(article.getArticleId());
            }
        }
        if (categoryService.deleteByPrimaryKey(id) == 1) {
            return super.buildMessage(0, "success", 1);
        } else {
            return super.buildMessage(1, "false", 0);
        }
    }

    @PostMapping("/update/{id}")
    public Message update(@PathVariable("id") Integer id, @RequestBody Category category) {
        Integer userId = UserUtil.getCurrentUserId();
        if (!(permissionService.hasCategoryPermission(userId, category.getCategorySiteId(), userId, CmsConst.PERMISSION_UPDATE))) {
            return super.buildMessage(2, "no permission", null);
        }
        category.setCategoryId(id);
        category.setCategoryUpdateTime(new Date());
        int count = categoryService.updateByPrimaryKeyWithBLOBs(category);
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
        category.setCategoryCreateTime(new Date());
        int count = categoryService.insert(category);
        if (count == 1) return new Message(0, "success", count);
        else return new Message(1, "false", count);
    }

    /**
     * 权限认证的函数
     *
     * @param id 栏目的id
     * @return
     */
    @GetMapping("/privalige/{id}")
    public Message judgePrivaludge(@PathVariable("id") Integer id) {
        Integer userId = UserUtil.getCurrentUserId();
        if (!(permissionService.hasCategoryPermission(userId, id, userId, CmsConst.PERMISSION_UPDATE))) {
            return super.buildMessage(2, "no permission", null);
        } else {
            return super.buildMessage(0, "yes", null);
        }
    }
}
