package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.admin.service.ContentWebService;
import com.ganster.cms.admin.util.CmsResultUtil;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.CategoryTree;
import com.ganster.cms.core.pojo.CategoryWithParent;
import com.ganster.cms.core.service.*;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
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
    @Autowired
    private ContentWebService contentWebService;
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @SystemControllerLog(description = "列出所有的栏目")
    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Category> pageInfo = contentWebService.listCategory(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "列出待审核的栏目")
    @GetMapping("/list/uncheck")
    public AjaxData listCheck(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {

        PageInfo<Category> pageInfo = contentWebService.listCheckCategory(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "选择栏目")
    @GetMapping("/select")
    public List<CategoryTree> select() {
        return contentWebService.select();
    }

    @SystemControllerLog(description = "删除单个栏目")
    @GetMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (contentWebService.deleteCategory(id)) {
            return new CmsResultUtil<>().setData(null);
        } else {
            return new CmsResultUtil<>().setError(null);
        }
    }

    @SystemControllerLog(description = "更新单个栏目")
    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, @RequestBody Category category) {
        if (contentWebService.updateCategory(id, category)) {
            return new CmsResultUtil<>().setData(null);
        } else {
            return new CmsResultUtil<>().setError(null);
        }
    }

    @SystemControllerLog(description = "查看单个栏目信息")
    @GetMapping("/details/{id}")
    public CategoryWithParent details(@PathVariable("id") Integer id) {
        return contentWebService.detailsCategory(id);
    }

    @SystemControllerLog(description = "添加栏目")
    @PostMapping("/add")
    public MessageDto add(@RequestBody Category category) {
        if (contentWebService.addCategory(category)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError(null);
    }

    @SystemControllerLog(description = "批量删除")
    @PostMapping("delete/batch")
    public MessageDto batchDelete(String categoryIdData) {
        if (contentWebService.deleteCategories(categoryIdData)) {
            return new CmsResultUtil<>().setError(null);
        }
        return new CmsResultUtil<>().setError(null);
    }

    @SystemControllerLog(description = "审核多个栏目")
    @GetMapping("/check/{categoryId}")
    public MessageDto checkCategory(@PathVariable Integer categoryId, @RequestParam Integer judge) {
        if (contentWebService.checkCategory(categoryId, judge)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError(null);
    }
}