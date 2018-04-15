package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.ContentWebService;
import com.ganster.cms.core.constant.CmsConst;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryTree;
import com.gangster.cms.common.pojo.CategoryWithParent;
import com.gangster.cms.common.pojo.User;
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
    private ContentWebService contentWebService;
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @SystemControllerLog(description = "列出所有的栏目")
    @GetMapping("/list")
    public AjaxData list(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Category> pageInfo = contentWebService.listCategory(user, siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    // TODO: 2018/4/15 列出当前用户待审核的栏目 
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
    public MessageDto delete(@SessionAttribute(CmsConst.CURRENT_USER) User user, @PathVariable("id") Integer id) {
        if (!contentWebService.deleteCategory(user, id)) {
            LOGGER.error("删除栏目id:{}失败", id);
            return MessageDto.fail(1, "删除栏目失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "更新单个栏目")
    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, @RequestBody Category category) {
        if (!contentWebService.updateCategory(id, category)) {
            LOGGER.error("更新栏目id为{}失败", id);
            return MessageDto.fail(1, "更新栏目失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "查看单个栏目信息")
    @GetMapping("/details/{id}")
    public CategoryWithParent details(@PathVariable("id") Integer id) {
        return contentWebService.detailsCategory(id);
    }

    @SystemControllerLog(description = "添加栏目")
    @PostMapping("/add")
    public MessageDto add(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestBody Category category) {
        if (!contentWebService.addCategory(user, category)) {
            LOGGER.error("添加栏目:{}失败", category.toString());
            return MessageDto.fail(1, "添加栏目失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "批量删除")
    @PostMapping("delete/batch")
    public MessageDto batchDelete(String categoryIdData) {
        if (!contentWebService.deleteCategories(categoryIdData)) {
            LOGGER.error("批量删除id组： {}失败", categoryIdData);
            return MessageDto.fail(1, "批量删除栏目失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "审核栏目")
    @GetMapping("/check/{categoryId}")
    public MessageDto checkCategory(@PathVariable Integer categoryId, @RequestParam Integer judge) {
        if (!contentWebService.checkCategory(categoryId, judge)) {
            LOGGER.error("审核栏目失败");
            return MessageDto.fail(1, "审核栏目失败");
        }
        return MessageDto.success(null);
    }
    // TODO: 2018/4/15 待添加批量审核 
}