package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.common.AjaxData;
import com.ganster.cms.admin.common.CategoryTree;
import com.ganster.cms.admin.common.Message;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.CategoryExample;
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

    @GetMapping("/list")
    @ResponseBody
    public AjaxData list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        CategoryExample categoryExample = new CategoryExample();
        PageInfo<Category> pageInfo;
        List<Category> list = categoryService.selectByExample(categoryExample);
        System.out.println(list.get(0));
        if (page != null && limit != null) {
            pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
            return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
        } else {
            pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
            return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
        }
    }

    @GetMapping("/select")
    @ResponseBody
    public List<CategoryTree> select(@RequestParam("id") Integer id) {
        List<CategoryTree> treeList = new ArrayList<>();
        if (id != -1) {
            Category category = categoryService.selectByPrimaryKey(id);
            CategoryTree tree = this.toTree(category);
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.or().andCategoryParentIdEqualTo(id);
            List<Category> list = categoryService.selectByExample(categoryExample);
            if (list.size() > 0) {
                for (Category c : list) {
                    treeList.add(toTree(c));
                }
                tree.setChildren(treeList);
            }
            return treeList;
        } else {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.or().andCategoryLevelEqualTo(1);
            List<Category> list = categoryService.selectByExample(categoryExample);
            for (Category category : list) {
                treeList.add(toTree(category));
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


    private CategoryTree toTree(Category category) {
        CategoryTree tree = new CategoryTree();
        int columnId = category.getCategoryId();
        tree.setId(columnId);
        tree.setName(category.getCategoryTitle());
        tree.setSpread(false);
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryParentIdEqualTo(category.getCategoryId());
        List<Category> list = categoryService.selectByExample(categoryExample);  //子栏目
        List<CategoryTree> categoryTrees = new ArrayList<>();
        if (list.size() > 0) {
            for (Category c : list) {
                CategoryTree categoryTree = toTree(c);
                categoryTrees.add(categoryTree);
            }
            tree.setChildren(categoryTrees);
        }
        return tree;
    }
}
