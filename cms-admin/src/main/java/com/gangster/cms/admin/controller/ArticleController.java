package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.ContentWebService;
import com.gangster.cms.common.pojo.Article;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create by Yoke on 2018/1/30
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    private final ContentWebService contentWebService;

    @Autowired
    public ArticleController(ContentWebService contentWebService) {
        this.contentWebService = contentWebService;
    }

    @SystemControllerLog(description = "列出所有的文章")
    @CmsPermission(moduleName = "文章管理")
    @GetMapping("/{siteId}")
    public AjaxData list(
            @SiteId @PathVariable("siteId") Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listArticle(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "列出待审核的文章")
    @CmsPermission(moduleName = "文章管理")
    @GetMapping("/check/{siteId}")
    public AjaxData listCheck(
            @SiteId @PathVariable("siteId") Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listCheckArticle(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }


    @SystemControllerLog(description = "添加文章")
    @CmsPermission(moduleName = "文章管理")
    @PostMapping("/{siteId}")
    public MessageDto add(
            @SiteId @PathVariable("siteId") Integer siteId,
            @RequestBody ArticleDTO articleDTO) {
        if (!contentWebService.addArticle(articleDTO)) {
            return MessageDto.fail(1, "添加文章失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "上传图片")
    @CmsPermission(moduleName = "文章管理")
    @PostMapping("/img/{siteId}")
    public MessageDto uploadImg(
            @SiteId @PathVariable("siteId") Integer siteId,
            @Param("file") MultipartFile file) {
        return MessageDto.success(contentWebService.uploadImg(file));
    }


    @SystemControllerLog(description = "删除单篇文章")
    @CmsPermission(moduleName = "文章管理")
    @DeleteMapping("/{siteId}/{articleId}")
    public MessageDto delete(
            @SiteId @PathVariable("siteId") Integer siteId,
            @PathVariable("articleId") Integer articleId) {

        if (!contentWebService.deleteSingleArticle(articleId)) {
            return MessageDto.fail(1, "删除文章失败");
        } else {
            return MessageDto.success(null);
        }
    }

    @SystemControllerLog(description = "查看单篇文章")
    @CmsPermission(moduleName = "文章管理")
    @GetMapping("/{siteId}/{articleId}")
    public ArticleDTO details(
            @SiteId @PathVariable("siteId") Integer siteId,
            @PathVariable("articleId") Integer articleId) {
        return contentWebService.detailsArticle(articleId);
    }

    @SystemControllerLog(description = "更新单篇文章")
    @PutMapping("/{siteId}/{articleId}")
    public MessageDto update(
            @SiteId @PathVariable("siteId") Integer siteId,
            @PathVariable("articleId") Integer articleId,
            @RequestBody ArticleDTO articleDTO) {
        if (!contentWebService.updateArticle(articleId, articleDTO)) {
            return MessageDto.fail(1, "更新文章失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "批处理删除")
    @CmsPermission(moduleName = "文章管理")
    @PostMapping("/batch/{siteId}")
    public MessageDto batchDelete(@SiteId @PathVariable("siteId") Integer siteId, String ids) {
        if (!contentWebService.deleteArticles(ids)) {
            return MessageDto.fail(1, "批处理失败");
        }
        LOGGER.info("批量删除文章ids{}成功", ids);
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "审核文章")
    @CmsPermission(moduleName = "文章管理")
    @PostMapping("/check/{siteId}/{articleId}")
    public MessageDto checkArticle(
            @SiteId @PathVariable("siteId") Integer siteId,
            @PathVariable Integer articleId,
            @RequestParam Integer judge) {
        if (!contentWebService.checkArticle(articleId, judge)) {
            return MessageDto.fail(1, "审核失败");
        }
        return MessageDto.success(null);
    }
}
