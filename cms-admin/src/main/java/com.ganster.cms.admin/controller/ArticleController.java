package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.config.ImgConfig;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.ArticleWithCategoryName;
import com.ganster.cms.admin.dto.ArticleWithTag;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.admin.util.PageUtil;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.Tag;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.TagService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImgConfig imgConfig;

    @Autowired
    private PermissionService permissionService;

    private Integer siteid;

    //
    @GetMapping("/list")
    @ResponseBody
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        siteid = siteId;
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId);
        List<Article> list = articleService.selectByExample(articleExample);
        if (list == null || list.isEmpty()) {
            return super.buildAjaxData(1, "no data", 0, null);
        } else {
            return PageUtil.getAjaxArticleData(page, limit, articleExample, articleService);
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public Message save(@RequestBody ArticleWithTag articleWithTag) {
        if (articleWithTag == null) {
            return super.buildMessage(1, "文章为空", null);
        }
        Article article = new Article(articleWithTag.getArticleTitle()
                , articleWithTag.getArticleType()
                , articleWithTag.getArticleAuthor()
                , articleWithTag.getArticleOrder()
                , articleWithTag.getArticleCategoryId()
                , articleWithTag.getArticleDesc()
                , articleWithTag.getArticleSkin()
                , articleWithTag.getArticleContent());
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteid);
        String tags = articleWithTag.getTags();
        List<String> tagList;
        int count = 0;
        if (!(tags == null || tags.isEmpty())) {
            tagList = Arrays.asList(tags.split(","));  //列出所有的tag标签
            for (String tag : tagList) {
                count += articleService.insertSelectiveWithTag(article, tag);
            }
        }

        return super.buildMessage(0, "success", count);
    }

    @GetMapping("/list/categorylist")
    @ResponseBody
    public AjaxData listArticleByColumnId(@RequestParam("id") Integer id, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        if (id != null) {
            ArticleExample articleExample = new ArticleExample();
            articleExample.or().andArticleCategoryIdEqualTo(id);
            return PageUtil.getAjaxArticleData(page, limit, articleExample, articleService);
        } else return super.buildAjaxData(1, "false", 0, null);
    }

    @PostMapping("/img")
    @ResponseBody
    public Map<String, Object> uploadImg(@Param("file") MultipartFile file) {
        String originalFileName = file.getOriginalFilename();   // 得到文件最初的名字
        LOGGER.info(originalFileName);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + originalFileName.substring(originalFileName.lastIndexOf("."));
//        Calendar date = Calendar.getInstance();
        /*File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH) + 1));*/
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
        Map<String, Object> map = new HashMap<>();
        map.put("src", fileUrl);
        return map;
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public Message delete(@PathVariable("id") Integer id) {
        Article article = articleService.selectByPrimaryKey(id);
        if (article == null) {
            return super.buildMessage(1, "false", 0);
        }
        int count = articleService.deleteArticleWithTags(id);
        if (count == 0) {
            return super.buildMessage(1, "false", 0);
        } else {
            return super.buildMessage(0, "success", 1);
        }
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    public ArticleWithCategoryName details(@PathVariable("id") Integer articleId) {
        LOGGER.info("通过了方法");
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article != null) {
            Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
            List<Tag> list = tagService.selectByArticleId(articleId);
            List<String> tagNameList = new ArrayList<>();
            if (!(list == null || list.isEmpty())) {
                for (Tag tag : list) {
                    tagNameList.add(tag.getTagName());
                }
            }
            String tags = String.join(",", tagNameList);
            return new ArticleWithCategoryName(category.getCategoryTitle(), tags, article);
        } else return null;
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Message update(@PathVariable("id") Integer id, @RequestBody Article article) {
        article.setArticleId(id);
        article.setArticleUpdateTime(new Date());
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleIdEqualTo(id);
        int count = articleService.updateByExampleSelective(article, articleExample);
        if (count == 1) return super.buildMessage(0, "succcess", count);
        else return super.buildMessage(1, "false", 1);
    }
}
