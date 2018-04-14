package com.ganster.cms.admin.service;

import com.ganster.cms.admin.dto.ArticleDTO;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SettingService;
import com.ganster.cms.core.service.TagService;
import com.ganster.cms.core.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yoke
 * Created on 2018/4/10
 */
// TODO: 2018/4/11 待添加权限
@Service
public class ContentWebService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private SettingService settingService;

    @Autowired
    private TagService tagService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentWebService.class);

    /**
     * 列出站点所有的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Article> listArticle(Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }

    /**
     * 列出该站点所有待审核的文章
     *
     * @param siteId
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Article> listCheckArticle(Integer siteId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleSiteIdEqualTo(siteId).andArticleStatusEqualTo(CmsConst.REVIEW);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }

    /**
     * 添加一篇文章
     *
     * @param articleDTO
     */
    public void addArticle(ArticleDTO articleDTO) {
        Category category = categoryService.selectByPrimaryKey(articleDTO.getArticleCategoryId());
        Integer siteId = category.getCategorySiteId();
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteId);
        article.setArticleStatus(CmsConst.REVIEW);

        String tags = articleDTO.getTags();
        List<String> tagList = Arrays.asList(tags.split(","));
        articleService.insertSelectiveWithTag(article, tagList);
    }


    /**
     * 列出某个栏目下的所有文章
     *
     * @param categoryId 栏目id
     * @param page
     * @param limit
     * @return
     */
    public PageInfo<Article> listCategoryOfArticle(Integer categoryId, Integer page, Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(categoryId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
    }

    /**
     * 上传文章图片内容
     *
     * @param file
     */
    public Map<String, Object> uploadImg(@Param("file") MultipartFile file) {
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
        return map;
    }

    /**
     * 删除单篇文章
     *
     * @param articleId
     * @return
     */
    public boolean deleteSingleArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }
        int count = 0;
        try {
            count += articleService.deleteByPrimaryKey(articleId);
        } catch (Exception e) {
            LOGGER.error("删除id为{}的文章发生{}错误", articleId, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 查看一篇文章的详细信息
     *
     * @param articleId
     * @return
     */
    public ArticleDTO detailArticle(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);

        if (article == null) {
            LOGGER.info("没有找到id为{}的文章信息", articleId);
            return null;
        }

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
    }


    /**
     * 更新单篇文章
     *
     * @param articleId  要更新的文章id
     * @param articleDTO 新的文章内容
     */
    public boolean updateArticle(Integer articleId, ArticleDTO articleDTO) {
        Article article = articleDTO.toArticle();
        article.setArticleId(articleId);
        article.setArticleStatus(0);
        article.setArticleUpdateTime(new Date());
        try {
            articleService.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            LOGGER.error("更新id为{}的文章发生错误", articleId);
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 批量删除文章
     *
     * @param articleIdData 文章id的集合
     * @return
     */
    public boolean deleteArticles(String articleIdData) {
        if (StringUtil.isNullOrEmpty(articleIdData)) {
            String[] articleIds = articleIdData.split(",");
            for (String articleId : articleIds) {
                try {
                    articleService.deleteArticleWithTags(Integer.parseInt(articleId));
                } catch (NumberFormatException e) {
                    LOGGER.error("删除多篇文章发生错误");
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }


    public boolean checkArticle(Integer articleId, Integer judge) {

        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) {
            LOGGER.info("没有找到id为{}的文章", articleId);
            return false;
        }
        article.setArticleStatus(judge);
        try {
            articleService.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            LOGGER.error("审核文章出现错误:{}", e.getMessage());
            return false;
        }
        return true;
    }

//    --------------------------------------------栏目部分--------------------------------------------------------------------


    public List<Category> listCategory(Integer siteId, Integer page, Integer limit) {
        return null;
    }




}
