package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.dto.TaskArticle;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.TagService;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimedTaskService {
    private static final Logger logger = LoggerFactory.getLogger(TimedTaskService.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private WebFileService webFileService;

    private static Integer firstArticleId;

    public Integer compareDate(List<Article> articles) {
        for (Article article : articles) {
            if (article.getArticleId().equals(articles.get(0).getArticleId())) {
                firstArticleId = article.getArticleId();
            } else {
                Article firstArticle = articleService.selectByPrimaryKey(firstArticleId);
                if (article.getArticleReleaseTime().before(firstArticle.getArticleReleaseTime())) {
                    firstArticleId = article.getArticleId();
                }
            }
        }
        return firstArticleId;
    }

    public boolean openArticleTask(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        article.setArticleReleaseStatus(true);
        return articleService.updateByPrimaryKeySelective(article) == 1;
    }

    public PageInfo<Article> findTaskArticle(Integer page, Integer limit) {
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectArticleByReleaseStatus(false));
    }

    public Boolean updateTaskArticle(Integer articleId, TaskArticle taskArticle) {
        Article newArticle = taskArticle.toArticle();
        Article oldArticle = articleService.selectByPrimaryKey(articleId);
        if (oldArticle == null) {
            logger.error("没有找到id为{}的定时文章", articleId);
            return false;
        }
        newArticle.setArticleId(articleId);
        newArticle.setArticleStatus(CmsConst.REVIEW);
        newArticle.setArticleUpdateTime(new Date());
        if (articleService.updateByPrimaryKeySelective(newArticle) == 1)
            return true;
        else return false;
    }

    public boolean addArticleTimedTask(TaskArticle taskArticle, Date releaseTime) {
        Category category = categoryService.selectByPrimaryKey(taskArticle.toArticle().getArticleCategoryId());
        Integer siteId = category.getCategorySiteId();
        Article article = taskArticle.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(siteId);
        article.setArticleReleaseTime(releaseTime);
        article.setArticleStatus(CmsConst.REVIEW);
        article.setArticleReleaseStatus(false);
        try {
            List<String> fileNames = null;
            if (taskArticle.getFileNames() != null) {
                fileNames = Arrays.asList(taskArticle.getFileNames().split(","));
            }

            WebFileExample webFileExample = new WebFileExample();
            webFileExample.or().andFileNameIn(fileNames);
            List<WebFile> files = webFileService.selectByExample(webFileExample);
            articleService.insertSelectiveWithTagAndFile(article, Arrays.asList(taskArticle.getTags().split(",")), files);

            for (WebFile webFile : files) {
                webFile.setFileArticleId(article.getArticleId());
            }

        } catch (Exception e) {
            logger.error("添加文章{}失败,错误原因{}", taskArticle, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public TaskArticle selectArticleById(Integer id) {
        Article article = articleService.selectByPrimaryKey(id);
        if (article == null)
            return null;
        else {
            TaskArticle taskArticle = new TaskArticle(article);
            Date date = article.getArticleReleaseTime();
            if (date != null) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = format.format(date);
                taskArticle.setReleasetime(dateString);
            }
            Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
            List<Tag> list = tagService.selectByArticleId(id);
            List<String> tagNameList
                    = list.stream().map(Tag::getTagName).collect(Collectors.toList());
            String tags = String.join(",", tagNameList);
            taskArticle.setCategoryName(category.getCategoryTitle());
            taskArticle.setTags(tags);
            return taskArticle;
        }
    }
}
