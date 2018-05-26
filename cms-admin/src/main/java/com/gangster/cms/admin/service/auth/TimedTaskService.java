package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.dto.TaskArticle;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.TagService;
import com.gangster.cms.admin.service.WebFileService;
import com.gangster.cms.admin.util.StringUtil;
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
import java.util.stream.Stream;

/**
 * 定时任务 Service
 */
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

    /**
     * 获得下一个定时发布的文章
     *
     * @param articles 未发布的所有文章
     * @return Date最靠前的文章Id
     */
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

    /**
     * 定时发布任务
     *
     * @param articleId 文章Id
     * @return 是否发布成功
     */
    public boolean openArticleTask(Integer articleId) {
        Article article = articleService.selectByPrimaryKey(articleId);
        article.setArticleReleaseStatus(true);
        return articleService.updateByPrimaryKeySelective(article) == 1;
    }

    /**
     * 查找所有未发布的文章
     *
     * @param page  页数
     * @param limit 每页的条数
     * @return 查找到的所有文章
     */
    public PageInfo<Article> findTaskArticle(Integer page, Integer limit) {
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectArticleByReleaseStatus(false));
    }

    /**
     * 更新定时发布文章的内容
     *
     * @param articleId   文章Id
     * @param taskArticle 获得更新后的文章
     * @return 是否更新成功
     */
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
        return articleService.updateByPrimaryKeySelective(newArticle) == 1;
    }

    /**
     * 添加定时发布文章
     *
     * @param taskArticle 文章内容
     * @param releaseTime 发布时间
     * @return 是否发布成功
     */
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

    /**
     * 选择单篇文章
     *
     * @param id 所选文章Id
     * @return 文章的内容
     */
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

    /**
     * 删除文章
     * @param articleIdList 文章Id字符串
     * @return 是否删除成功
     */
    public boolean deleteArticles(String articleIdList) {
        if (!StringUtil.isNullOrEmpty(articleIdList)) {
            String[] articleIds = articleIdList.split(",");
            Stream.of(articleIds).forEach(e -> articleService.deleteArticleWithTagsAndFiles(Integer.parseInt(e)));
            return true;
        } else {
            return false;
        }
    }
}
