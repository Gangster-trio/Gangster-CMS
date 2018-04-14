package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.admin.service.ContentWebService;
import com.ganster.cms.core.pojo.Article;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Create by Yoke on 2018/1/30
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ContentWebService contentWebService;


    @SystemControllerLog(description = "列出所有的文章")
    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listArticle(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 列出某站点待审核的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/list/check")
    public AjaxData listCheck(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listCheckArticle(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/save")
    public Message save(@RequestBody ArticleDTO articleDTO) {
        if (contentWebService.addArticle(articleDTO)) {
            return new Message(0, "success", null);
        }
        return new Message(1, "failed", null);
    }

    // TODO: 2018/4/14 待添加权限
    @GetMapping("/list/category")
    public AjaxData listArticleByColumnId(
            @RequestParam("id") Integer id,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        PageInfo<Article> pageInfo = contentWebService.listCategoryOfArticle(id, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/img")
    public Message uploadImg(@Param("file") MultipartFile file) {
        Map<String, Object> map = contentWebService.uploadImg(file);
        return new Message(0, "success", map);
    }

    @GetMapping("/delete/{id}")
    public Message delete(@PathVariable("id") Integer id) {

        if (contentWebService.deleteSingleArticle(id)) {
            return new Message(0, "success", null);
        } else {
            return new Message(1, "failed");
        }

    }

    @GetMapping("/details/{id}")
    public ArticleDTO details(@PathVariable("id") Integer articleId) {
        return contentWebService.detailArticle(articleId);
    }

    @PostMapping("/update/{id}")
    public Message update(@PathVariable("id") Integer id, @RequestBody ArticleDTO articleDTO) {
        if (contentWebService.updateArticle(id, articleDTO)) {
            return new Message(0, "success", null);
        }
        return new Message(1, "failed", null);
    }

    // TODO: 2018/4/14 待添加权限判断
    @PostMapping("/delete/batch")
    public Message batchDelete(String articleIdData) {
        if (contentWebService.deleteArticles(articleIdData)) {
            return new Message(0, "success", null);
        }
        return new Message(1, "failed", null);
    }

    @GetMapping("/check/{articleId}")
    public Message checkArticle(@PathVariable Integer articleId, @RequestParam Integer judge) {
        if (contentWebService.checkArticle(articleId, judge)) {
            return new Message(0, "success", null);
        }
        return new Message(1, "failed", null);
    }
}
