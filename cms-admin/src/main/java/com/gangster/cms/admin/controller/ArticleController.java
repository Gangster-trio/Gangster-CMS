package com.gangster.cms.admin.controller;


import com.alibaba.fastjson.JSONObject;
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

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    @SystemControllerLog(description = "列出待审核的文章")
    @GetMapping("/list/uncheck")
    public AjaxData listCheck(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = contentWebService.listCheckArticle(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }


    @SystemControllerLog(description = "添加文章")
    @PostMapping("/add")
    public MessageDto add(HttpServletRequest request) {
        ArticleDTO articleDTO = transformPOJO(request);
        if (!contentWebService.addArticle(articleDTO)) {
            LOGGER.error("添加文章失败");
            return MessageDto.fail(1, "添加文章失败");
        }
        return MessageDto.success(null);
    }


    @SystemControllerLog(description = "列出某个栏目的文章")
    @GetMapping("/list/category")
    public AjaxData listArticleByColumnId(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        PageInfo<Article> pageInfo = contentWebService.listCategoryOfArticle(categoryId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "上传图片")
    @PostMapping("/img")
    public MessageDto uploadImg(@Param("file") MultipartFile file) {
        return MessageDto.success(contentWebService.uploadImg(file));
    }


    @SystemControllerLog(description = "删除单篇文章")
    @GetMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {

        if (!contentWebService.deleteSingleArticle(id)) {
            LOGGER.error("删除文章id为{}失败", id);
            return MessageDto.fail(1, "删除文章失败");
        } else {
            return MessageDto.success(null);
        }

    }

    @SystemControllerLog(description = "查看单篇文章")
    @GetMapping("/details/{id}")
    public ArticleDTO details(@PathVariable("id") Integer articleId) {
        return contentWebService.detailArticle(articleId);
    }

    @SystemControllerLog(description = "更新单篇文章")
    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, HttpServletRequest request) {
        ArticleDTO articleDTO = transformPOJO(request);
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

    private static ArticleDTO transformPOJO(HttpServletRequest request) {
        BufferedReader br = null;
        ArticleDTO articleDTO;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            articleDTO = JSONObject.parseObject(sb.toString(), ArticleDTO.class);
        } catch (IOException e) {
            LOGGER.error("转换出错,错误信息：{}", e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            closeResource(br, LOGGER);
        }
        return articleDTO;
    }

    public static void closeResource(BufferedReader br, Logger logger) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("关闭流出错");
                e.printStackTrace();
            }
        }
    }
}
