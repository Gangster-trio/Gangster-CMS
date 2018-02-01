package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.config.ImgConfig;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.CategoryWithArticel;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Create by Yoke on 2018/1/30
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImgConfig imgConfig;

    @GetMapping("/list")
    @ResponseBody
    public AjaxData list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        List<Article> list = articleService.selectByExample(articleExample);
        return getAjaxData(page, limit, articleExample);
    }

    @PostMapping("/save")
    @ResponseBody
    public Message save(@RequestBody Article article) {
        if (article != null) {
            article.setArticleCreateTime(new Date());
            int count = articleService.insert(article);
            return super.buildMessage(0, "success", count);
        } else {
            return super.buildMessage(1, "false", 0);
        }
    }

    @GetMapping("/list/columnlist")
    @ResponseBody
    public AjaxData listArticleByColumnId(@RequestParam("id") Integer id, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        if (id != null) {
            ArticleExample articleExample = new ArticleExample();
            articleExample.or().andArticleCategoryIdEqualTo(id);
            List<Article> list = articleService.selectByExample(articleExample);
            return getAjaxData(page, limit, articleExample);
        } else return super.buildAjaxData(1, "false", 0, null);
    }

    private AjaxData getAjaxData(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, ArticleExample articleExample) {
        if (page != null && limit != null) {
            PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) articleService.selectByExample(articleExample));
        } else {
            PageInfo<Article> pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) articleService.selectByExample(articleExample));
        }
    }

    @PostMapping("/img")
    @ResponseBody
    public Map<String, Object> uploadImg(HttpServletRequest request, @Param("file") MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();   // 得到文件最初的名字
        LOGGER.info(originalFileName);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + originalFileName.substring(originalFileName.lastIndexOf("."));
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH) + 1));
        File newFile = new File(imgConfig.getSaveLocation() + File.separator + newName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        LOGGER.info("文件上传" + newFile.toString());
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileUrl = "/upload/" + newName;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("src", fileUrl);
        return map;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        Article article = articleService.selectByPrimaryKey(id);
        if (article != null) {
            int count = articleService.deleteByPrimaryKey(id);
            return super.buildMessage(0, "success", count);
        } else return super.buildMessage(1, "false", 1);
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    public CategoryWithArticel details(@PathVariable("id") Integer articleId) {
        LOGGER.info("通过了方法");
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article != null) {
            Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
            return new CategoryWithArticel(category.getCategoryTitle(), article);
        } else return null;
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Message update(@PathVariable("id") Integer id, @RequestBody Article article) {
        article.setArticleId(id);
        article.setArticleUpdateTime(new Date());
        int count = articleService.updateByPrimaryKeyWithBLOBs(article);
        if (count == 1) return super.buildMessage(0, "succcess", count);
        else return super.buildMessage(1, "false", 1);
    }
}
