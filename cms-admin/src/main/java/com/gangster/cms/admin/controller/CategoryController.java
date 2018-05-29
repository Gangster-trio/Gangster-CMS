package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.ContentWebService;
import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.pojo.Category;
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

    private final ContentWebService contentWebService;
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    public CategoryController(ContentWebService contentWebService) {
        this.contentWebService = contentWebService;
    }

    @SystemControllerLog(description = "列出所有的栏目")
    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Category> pageInfo = contentWebService.listCategory(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "列出待审核的栏目")
    @GetMapping("/list/check")
    public AjaxData listCheck(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {

        PageInfo<Category> pageInfo = contentWebService.listCheckCategory(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "选择栏目")
    @GetMapping("/select/{siteId}")
    public List<CategoryTree> select(@PathVariable("siteId") Integer siteId) {
        return contentWebService.select(siteId);
    }

    @SystemControllerLog(description = "删除栏目")
    @DeleteMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (!contentWebService.deleteCategory(id)) {
            return MessageDto.fail(1, "删除栏目失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "更新单个栏目")
    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, @RequestBody Category category) {
        if (!contentWebService.updateCategory(id, category)) {
            return MessageDto.fail(1, "更新栏目失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "查看单个栏目信息")
    @GetMapping("/details/{id}")
    public MessageDto details(@PathVariable("id") Integer id) {
        return MessageDto.success(contentWebService.detailsCategory(id));
    }

    @SystemControllerLog(description = "添加栏目")
    @PostMapping("/add")
    public MessageDto add(@RequestBody Category category) {
        if (!contentWebService.addCategory(category)) {
            return MessageDto.fail(1, "添加栏目失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "批量删除")
    @PostMapping("/delete/batch")
    public MessageDto batchDelete(String categoryIds) {
        if (!contentWebService.deleteCategories(categoryIds)) {
            return MessageDto.fail(1, "批量删除栏目失败");
        }
        LOGGER.info("批量删除id组：{}成功", categoryIds);
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "审核栏目")
    @GetMapping("/check/{id}")
    public MessageDto checkCategory(@PathVariable Integer id, @RequestParam Integer judge) {
        if (!contentWebService.checkCategory(id, judge)) {
            return MessageDto.fail(1, "审核栏目失败");
        }
        return MessageDto.success(null);
    }
}