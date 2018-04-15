package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.annotation.CheckParam;
import com.ganster.cms.admin.annotation.CheckType;
import com.ganster.cms.admin.annotation.CmsPermission;
import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.admin.service.ContentWebService;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.User;
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
    public AjaxData list(@SessionAttribute(CmsConst.CURRENT_USER) User user, @CheckParam @RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listArticle(user, siteId, page, limit);
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
    public MessageDto save(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestBody ArticleDTO articleDTO) {
        if (!contentWebService.addArticle(articleDTO, user)) {
            LOGGER.error("添加文章失败");
            return MessageDto.fail(1, "添加文章失败");
        }
        return MessageDto.success(null);
    }

    // TODO: 2018/4/15 列出当前登录用户的待审核文章 
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
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "删除单篇文章")
    @CmsPermission(checkType = CheckType.ARTICLE_WRITE)
    @GetMapping("/delete/{id}")
    public MessageDto delete(@CheckParam @PathVariable("id") Integer id) {

        if (!contentWebService.deleteSingleArticle(id)) {
            LOGGER.error("删除文章id为{}失败", id);
            return MessageDto.fail(1, "删除文章失败");
        } else {
            return MessageDto.success(null);
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
        if (!contentWebService.updateArticle(id, articleDTO)) {
            LOGGER.error("更新单篇文章id为{}失败", id);
            return MessageDto.fail(1, "删除文章失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "批处理删除")
    @PostMapping("/delete/batch")
    public MessageDto batchDelete(String articleIdData) {
        if (!contentWebService.deleteArticles(articleIdData)) {
            LOGGER.error("批处理删除文章失败");
            return MessageDto.fail(1, "批处理失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "审核多篇文章")
    @GetMapping("/check/{articleId}")
    public MessageDto checkArticle(@PathVariable Integer articleId, @RequestParam Integer judge) {
        if (!contentWebService.checkArticle(articleId, judge)) {
            LOGGER.error("审核失败");
            return MessageDto.fail(1, "审核失败");
        }
        return MessageDto.success(null);
    }
}
