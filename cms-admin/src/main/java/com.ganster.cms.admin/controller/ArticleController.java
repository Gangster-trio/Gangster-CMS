package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.config.ImgConfig;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.*;
import com.ganster.cms.core.util.PermissionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
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
    private SettingService settingService;

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

    @GetMapping("/list")
    @ResponseBody
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {

        siteid = siteId;

        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 10;
        }
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId);
        PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
        List<Article> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return super.buildAjaxData(1, "no data", 0, null);
        } else {
            return super.buildAjaxData(0, "success", pageInfo.getTotal(), list);
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public Message save(@RequestBody ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return super.buildMessage(1, "文章为空", null);
        }
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteid);
        String tags = articleDTO.getTags();
        List<String> tagList;
        int count = 0;
        if (!(tags == null || tags.isEmpty())) {
            tagList = Arrays.asList(tags.split(","));  //列出所有的tag标签
            count += articleService.insertSelectiveWithTag(article, tagList);
        }

        return super.buildMessage(0, "success", count);
    }

    @GetMapping("/list/category")
    @ResponseBody
    public AjaxData listArticleByColumnId(
            @RequestParam("id") Integer id,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        if (id != null) {
            if (page == null || page == 0) {
                page = 1;
            }
            if (limit == null || limit == 0) {
                limit = 10;
            }
            ArticleExample articleExample = new ArticleExample();
            articleExample.or().andArticleCategoryIdEqualTo(id);
            PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            List<Article> list = pageInfo.getList();
            if (list == null || list.isEmpty()) {
                return super.buildAjaxData(1, "no data", 0, null);
            }
            return super.buildAjaxData(0, "success", pageInfo.getTotal(), list);
        } else {
            return super.buildAjaxData(1, "false", 0, null);
        }
    }

    @PostMapping("/img")
    @ResponseBody
    public Message uploadImg(@Param("file") MultipartFile file) {
        String originalFileName = file.getOriginalFilename();   // 得到文件最初的名字
        LOGGER.info(originalFileName);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + originalFileName.substring(originalFileName.lastIndexOf("."));
        File dir = new File(settingService.get(CmsConst.PIC_PATH_SETTING));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, newName);
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("文件上传" + newFile.toString());
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
//            return new Message(2, "存储文件错误", null);
        }
        String fileUrl = "/pic/" + newName;
        Map<String, Object> map = new HashMap<>();
        map.put("src", fileUrl);

        return new Message(0, "success", map);
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
    public ArticleDTO details(@PathVariable("id") Integer articleId) {
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
            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTO.setCategoryName(category.getCategoryTitle());
            articleDTO.setTags(tags);
            return articleDTO;
        } else return null;
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public Message update(@PathVariable("id") Integer id, @RequestBody ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return super.buildMessage(1, "文章为空", null);
        }
        Article article = articleDTO.toArticle();
        article.setArticleId(id);
        article.setArticleUpdateTime(new Date());
        articleService.updateByPrimaryKeySelective(article);
        return super.buildMessage(0, "success", "success");
    }
}
