package com.gangster.cms.admin.util;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryExample;
import com.ganster.cms.core.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Yoke on 2018/2/4
 */
public class PageUtil {

    public static AjaxData getAjaxCateogryData(Integer page, Integer limit, CategoryExample categoryExample, CategoryService categoryService, List list) {
        PageInfo<Category> pageInfo;
        if (page != null && limit != null) {
            pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
            return new AjaxData(0, "success", pageInfo.getTotal(), (ArrayList) list);
        } else {
            pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
            return new AjaxData(0, "success", pageInfo.getTotal(), (ArrayList) list);
        }
    }
}
