package com.ganster.cms.admin.util;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.CategoryExample;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Yoke on 2018/2/4
 */
public class PageUtil {
    public static AjaxData getAjaxArticleData(Integer page, Integer limit, ArticleExample articleExample, ArticleService articleService) {
        PageInfo<Article> pageInfo;
        if (page != null && limit != null) {
             pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            return new AjaxData(0, "success", pageInfo.getSize(), (ArrayList) articleService.selectByExample(articleExample));
        } else {
            pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            return new AjaxData(0, "success", pageInfo.getSize(), (ArrayList) articleService.selectByExample(articleExample));
        }
    }

    public static AjaxData getAjaxCateogryData(Integer page, Integer limit, CategoryExample categoryExample, CategoryService categoryService, List list) {
        PageInfo<Category> pageInfo;
        if (page != null && limit != null) {
            pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
            return new AjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
        } else {
            pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> categoryService.selectByExample(categoryExample));
            return new AjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
        }
    }
}
