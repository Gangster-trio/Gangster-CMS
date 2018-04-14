package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.*;
import com.ganster.cms.core.util.PermissionUtil;
import com.ganster.cms.core.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Create by Yoke on 2018/1/30
 */
@RestController
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
    private PermissionService permissionService;
    @Autowired
    private UserService userService;
    private static final String ADMIN = "admin";

    @SystemControllerLog(description = "列出所有的文章")
    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");

        List<Integer> categoryIdList = PermissionUtil.getAllPermittedCategory(uid, siteId, CmsConst.PERMISSION_READ);
        if (categoryIdList == null || categoryIdList.isEmpty()) {
            return super.buildAjaxData(2, "no privilege", 0, null);
        }
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleCategoryIdIn(categoryIdList);
        PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
        List<Article> list = pageInfo.getList();
        return super.buildAjaxData(0, "success", pageInfo.getTotal(), list);
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
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        if (!userService.isAdmin(uid)) {
            return super.buildAjaxData(2, "null privilege", 0, null);
        }
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleStatusEqualTo(0);
        PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
        List<Article> list = pageInfo.getList();
        return super.buildAjaxData(0, "success", pageInfo.getTotal(), list);
    }


    @PostMapping("/save")
    public Message save(@RequestBody ArticleDTO articleDTO) {
        // 得到用户的id
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");

        // 得到添加文章的网站的id
        Category category = categoryService.selectByPrimaryKey(articleDTO.getArticleCategoryId());
        Integer siteId = category.getCategorySiteId();

        if (!permissionService.hasCategoryPermission(userId, siteId, category.getCategoryId(), CmsConst.PERMISSION_WRITE)) {
            return super.buildMessage(2, "no permission", null);
        }

        // 插入文章和标签
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteId);
        article.setArticleStatus(0);
        String tags = articleDTO.getTags();
        List<String> tagList;
        int count = 0;
        if (!(tags == null || tags.isEmpty())) {
            //列出所有的tag标签
            tagList = Arrays.asList(tags.split(","));
            count += articleService.insertSelectiveWithTag(article, tagList);
        }

        // 插入权限表操作
        try {
            User user = userService.selectByPrimaryKey(userId);
            permissionService.addCategoryPermissionToUser(userId, siteId, category.getCategoryId(), CmsConst.PERMISSION_READ);
            permissionService.addCategoryPermissionToUser(userId, siteId, category.getCategoryId(), CmsConst.PERMISSION_WRITE);
            addPermission(category, user, userService, permissionService);
            PermissionUtil.flush(userId);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return super.buildMessage(1, "false", "用户找不见");
        }
        return super.buildMessage(0, "success", count);
    }

    static void addPermission(Category category, User user, UserService userService, PermissionService permissionService) throws UserNotFoundException {
        if (!ADMIN.equals(user.getUserName())) {
            // 得到超级管理员的id
            UserExample userExample = new UserExample();
            userExample.or().andUserNameEqualTo("admi");
            User u = userService.selectByExample(userExample).get(0);
            permissionService.addCategoryPermissionToUser(u.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_READ);
            permissionService.addCategoryPermissionToUser(u.getUserId(), category.getCategorySiteId(), category.getCategoryId(), CmsConst.PERMISSION_WRITE);
        }
    }

    @GetMapping("/list/category")
    public AjaxData listArticleByColumnId(
            @RequestParam("id") Integer id,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(id);
        PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
        List<Article> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return super.buildAjaxData(1, "no data", 0, null);
        }
        return super.buildAjaxData(0, "success", pageInfo.getTotal(), list);
    }

    @PostMapping("/img")
    public Message uploadImg(@Param("file") MultipartFile file) {
        // 得到文件最初的名字
        String originalFileName = file.getOriginalFilename();
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
        }
        String fileUrl = "/pic/" + newName;
        Map<String, Object> map = new HashMap<>();
        map.put("src", fileUrl);

        return new Message(0, "success", map);
    }

    @GetMapping("/delete/{id}")
    public Message delete(@PathVariable("id") Integer id) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        Article article = articleService.selectByPrimaryKey(id);
        if (article == null) {
            return super.buildMessage(1, "false", 0);
        }

        if (!permissionService.hasCategoryPermission(userId, article.getArticleSiteId(), article.getArticleCategoryId(), CmsConst.PERMISSION_WRITE)) {
            return super.buildMessage(2, "no permission", null);
        }


        int count = articleService.deleteArticleWithTags(id);
        if (count == 0) {
            return super.buildMessage(1, "false", 0);
        } else {
            return super.buildMessage(0, "success", 1);
        }
    }

    @GetMapping("/details/{id}")
    public ArticleDTO details(@PathVariable("id") Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article != null) {
            Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
            List<Tag> list = tagService.selectByArticleId(articleId);
            List<String> tagNameList = null;
            if (!(list == null || list.isEmpty())) {
                tagNameList = list.stream().map(Tag::getTagName).collect(Collectors.toList());
            }
            assert tagNameList != null;
            String tags = String.join(",", tagNameList);
            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTO.setCategoryName(category.getCategoryTitle());
            articleDTO.setTags(tags);
            return articleDTO;
        } else {
            return null;
        }
    }

    @PostMapping("/update/{id}")
    public Message update(@PathVariable("id") Integer id, @RequestBody ArticleDTO articleDTO) {
        Article article = articleDTO.toArticle();
        article.setArticleId(id);
        article.setArticleStatus(0);
        article.setArticleUpdateTime(new Date());
        articleService.updateByPrimaryKeySelective(article);
        return super.buildMessage(0, "success", "success");
    }

    @PostMapping("/delete/batch")
    public Message batchDelete(String articleIdData) {
        if (StringUtil.isNullOrEmpty(articleIdData)) {
            String[] articleIds = articleIdData.split(",");
            int count = 0;
            for (String articleId : articleIds) {
                count += articleService.deleteArticleWithTags(Integer.parseInt(articleId));
            }
            if (count != 0) {
                return super.buildMessage(0, "success", "success");
            } else {
                return super.buildMessage(1, "false", null);
            }
        } else {
            return super.buildMessage(1, "false", null);
        }
    }

    @GetMapping("/check/{articleId}")
    public Message checkArticle(@PathVariable Integer articleId, @RequestParam Integer judge) {

        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) {
            return super.buildMessage(1, "没有找到对应的文章", null);
        }
        article.setArticleStatus(judge);
        int count = 0;
        try {
            count += articleService.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            LOGGER.error("审核文章出现错误:{}", e.getMessage());
            return super.buildMessage(1, "审核文章出现错误，请重试", null);
        }
        if (count == 0) {
            return super.buildMessage(1, "审核文章出现错误，请重试", null);
        } else {
            return super.buildMessage(0, "success", "success");
        }
    }
}
