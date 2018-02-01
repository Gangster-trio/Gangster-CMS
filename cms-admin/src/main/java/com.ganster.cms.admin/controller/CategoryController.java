package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    @ResponseBody
    public AjaxData list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        PageInfo<Category> pageInfo;
        List<Category> list = categoryService.selectByExample(categoryExample);
//        System.out.println(list.get(0));
        if (!list.isEmpty()) {
            if (page != null && limit != null) {
                pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
                return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
            } else {
                pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
                return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
            }
        } else {
            return super.buildAjaxData(1, "no data", 0, null);
        }
    }

    @GetMapping("/select")
    @ResponseBody
    public List<CategoryTree> select(@RequestParam("id") Integer id) {
        List<CategoryTree> treeList = new ArrayList<>();
        if (id != -1) {
            Category category = categoryService.selectByPrimaryKey(id);
            CategoryTree tree = categoryService.toTree(category);
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.or().andCategoryParentIdEqualTo(id);
            List<Category> list = categoryService.selectByExample(categoryExample);
            if (list.size() > 0) {
                for (Category c : list) {
                    treeList.add(categoryService.toTree(c));
                }
                tree.setChildren(treeList);
            }
            return treeList;
        } else {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.or().andCategoryLevelEqualTo(1);
            List<Category> list = categoryService.selectByExample(categoryExample);
            for (Category category : list) {
                treeList.add(categoryService.toTree(category));
            }
            return treeList;
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public Message add(@RequestBody Category category) {
        category.setCategoryCreateTime(new Date());
        int count = categoryService.insert(category);
        if (count == 1) return new Message(0, "success", count);
        else return new Message(1, "false", count);
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        List<Article> list = articleService.selectArticleByCategoryId(id);
        if (!list.isEmpty()) {
            for (Article article : list) {
                articleService.deleteByPrimaryKey(article.getArticleId());
            }
        }
        if (categoryService.deleteByPrimaryKey(id) == 1) return super.buildMessage(0, "success", 1);
        else return super.buildMessage(1, "false", 0);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Message update(@PathVariable("id") Integer id, @RequestBody Category category) {
        category.setCategoryId(id);
        category.setCategoryUpdateTime(new Date());
        int count = categoryService.updateByPrimaryKeyWithBLOBs(category);
        if (count == 1) return super.buildMessage(0, "success", 1);
        else return super.buildMessage(1, "false", 0);
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    public CategoryWithParent details(@PathVariable("id") Integer id) {
        Category category = categoryService.selectByPrimaryKey(id);
        if (category != null) return new CategoryWithParent(category.getCategoryTitle(), category);
        else return null;
    }
}
