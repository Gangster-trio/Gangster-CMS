package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.annotation.CheckParam;
import com.ganster.cms.admin.annotation.CheckType;
import com.ganster.cms.admin.annotation.CmsPermission;
import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.admin.service.ContentWebService;
import com.ganster.cms.admin.util.CmsResultUtil;
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

    @CmsPermission(checkType = CheckType.ARTICLE_READ)
    @SystemControllerLog(description = "列出所有的文章")
    @GetMapping("/list")
    public AjaxData list(@CheckParam @RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
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
    @SystemControllerLog(description = "列出待审核的文章")
    @CmsPermission(checkType = CheckType.ARTICLE_WRITE)
    @GetMapping("/list/uncheck")
    public AjaxData listCheck(@CheckParam @RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listCheckArticle(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }


    @SystemControllerLog(description = "添加文章")
    @PostMapping("/save")
    public MessageDto save(@RequestBody ArticleDTO articleDTO) {
        if (contentWebService.addArticle(articleDTO)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError(null);
    }

    @SystemControllerLog(description = "列出某个栏目的文章")
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

    @SystemControllerLog(description = "上传图片")
    @PostMapping("/img")
    public MessageDto uploadImg(@Param("file") MultipartFile file) {
        Map<String, Object> map = contentWebService.uploadImg(file);
        return new CmsResultUtil<>().setData(null);
    }

    @SystemControllerLog(description = "删除单篇文章")
    @CmsPermission(checkType = CheckType.ARTICLE_WRITE)
    @GetMapping("/delete/{id}")
    public MessageDto delete(@CheckParam @PathVariable("id") Integer id) {

        if (contentWebService.deleteSingleArticle(id)) {
            return new CmsResultUtil<>().setData(null);
        } else {
            return new CmsResultUtil<>().setError(null);
        }

    }

    @SystemControllerLog(description = "查看单篇文章")
    @CmsPermission(checkType = CheckType.ARTICLE_WRITE)
    @GetMapping("/details/{id}")
    public ArticleDTO details(@CheckParam @PathVariable("id") Integer articleId) {
        return contentWebService.detailArticle(articleId);
    }

    @SystemControllerLog(description = "更新单篇文章")
    @CmsPermission(checkType = CheckType.ARTICLE_WRITE)
    @PostMapping("/update/{id}")
    public MessageDto update(@CheckParam @PathVariable("id") Integer id, @RequestBody ArticleDTO articleDTO) {
        if (contentWebService.updateArticle(id, articleDTO)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError(null);
    }

    @SystemControllerLog(description = "批处理删除")
    @PostMapping("/delete/batch")
    public MessageDto batchDelete(String articleIdData) {
        if (contentWebService.deleteArticles(articleIdData)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError(null);
    }

    @SystemControllerLog(description = "审核多篇文章")
    @GetMapping("/check/{articleId}")
    public MessageDto checkArticle(@PathVariable Integer articleId, @RequestParam Integer judge) {
        if (contentWebService.checkArticle(articleId, judge)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError(null);
    }
}
