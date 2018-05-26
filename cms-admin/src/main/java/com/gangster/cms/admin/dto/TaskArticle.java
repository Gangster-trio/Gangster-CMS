package com.gangster.cms.admin.dto;
import com.gangster.cms.common.pojo.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskArticle implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(TaskArticle.class);
    private Integer articleId;
    private String articleTitle;
    private String articleType;
    private String articleAuthor;
    private String articleUrl;
    private Integer articleOrder;
    private Integer articleSiteId;
    private Integer articleCategoryId;
    private Date articleCreateTime;
    private Date articleUpdateTime;
    private String articleThumb;
    private Integer articleHit;
    private String articleDesc;
    private Integer articleStatus;
    private String articleSkin;
    private Boolean articleInHomepage;
    private String articleContent;
    private String releasetime;
    private Boolean releaseStatus;

    public TaskArticle() {
    }

    private String categoryName;
    private String fileNames;
    private String tags;

    public TaskArticle(String articleTitle,
                       String articleType,
                       String articleAuthor,
                       Integer articleOrder,
                       Integer articleCategoryId,
                       String articleThumb,
                       String articleDesc,
                       String articleSkin,
                       String articleContent,
                       String fileNames,
                       String tags,
                       String releasetime,
                       boolean releaseStatus
    ) {
        setArticleAuthor(articleAuthor);
        setArticleCategoryId(articleCategoryId);
        setArticleSkin(articleSkin);
        setArticleContent(articleContent);
        setArticleDesc(articleDesc);
        setArticleOrder(articleOrder);
        setArticleThumb(articleThumb);
        setArticleType(articleType);
        setArticleTitle(articleTitle);
        setFileNames(fileNames);
        setTags(tags);
        setReleasetime(releasetime);
        setReleaseStatus(releaseStatus);
    }

    public TaskArticle(Article article) {
        setArticleId(article.getArticleId());
        setArticleTitle(article.getArticleTitle());
        setArticleType(article.getArticleType());
        setArticleAuthor(article.getArticleAuthor());
        setArticleUrl(article.getArticleUrl());
        setArticleOrder(article.getArticleOrder());
        setArticleSiteId(article.getArticleSiteId());
        setArticleCategoryId(article.getArticleCategoryId());
        setArticleCreateTime(article.getArticleCreateTime());
        setArticleUpdateTime(article.getArticleUpdateTime());
        setArticleThumb(article.getArticleThumb());
        setArticleHit(article.getArticleHit());
        setArticleDesc(article.getArticleDesc());
        setArticleStatus(article.getArticleStatus());
        setArticleSkin(article.getArticleSkin());
        setArticleInHomepage(article.getArticleInHomepage());
        setArticleContent(article.getArticleContent());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Boolean releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Article toArticle() {
        Article article = new Article();
        article.setArticleTitle(getArticleTitle());
        article.setArticleSkin(getArticleSkin());
        article.setArticleAuthor(getArticleAuthor());
        article.setArticleCreateTime(getArticleCreateTime());
        article.setArticleSiteId(getArticleSiteId());
        article.setArticleUpdateTime(getArticleUpdateTime());
        article.setArticleHit(getArticleHit());
        article.setArticleCategoryId(getArticleCategoryId());
        article.setArticleContent(getArticleContent());
        article.setArticleDesc(getArticleDesc());
        article.setArticleInHomepage(getArticleInHomepage());
        article.setArticleOrder(getArticleOrder());
        article.setArticleStatus(getArticleStatus());
        article.setArticleType(getArticleType());
        article.setArticleThumb(getArticleThumb());
        article.setArticleUrl(getArticleUrl());
        article.setArticleReleaseStatus(getReleaseStatus());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(getReleasetime());
            article.setArticleReleaseTime(date);
        } catch (ParseException e) {
            logger.info("时间转化错误");
            e.printStackTrace();
        }
        return article;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public Integer getArticleSiteId() {
        return articleSiteId;
    }

    public void setArticleSiteId(Integer articleSiteId) {
        this.articleSiteId = articleSiteId;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public String getArticleThumb() {
        return articleThumb;
    }

    public void setArticleThumb(String articleThumb) {
        this.articleThumb = articleThumb;
    }

    public Integer getArticleHit() {
        return articleHit;
    }

    public void setArticleHit(Integer articleHit) {
        this.articleHit = articleHit;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleSkin() {
        return articleSkin;
    }

    public void setArticleSkin(String articleSkin) {
        this.articleSkin = articleSkin;
    }

    public Boolean getArticleInHomepage() {
        return articleInHomepage;
    }

    public void setArticleInHomepage(Boolean articleInHomepage) {
        this.articleInHomepage = articleInHomepage;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getReleasetime() {
        return releasetime;
    }


    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }




}
