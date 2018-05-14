package com.gangster.cms.admin.dto;

import com.gangster.cms.common.pojo.Article;

import java.util.Date;

public class TaskArticle extends Article {
    private String categoryName;
    private String fileNames;
    private String tags;
    private Date releasetime;

    public TaskArticle() {
        super();
    }

    public TaskArticle(Article taskArticle) {
        setArticleId(taskArticle.getArticleId());
        setArticleAuthor(taskArticle.getArticleAuthor());
        setArticleCategoryId(taskArticle.getArticleCategoryId());
        setArticleCreateTime(taskArticle.getArticleCreateTime());
        setArticleUpdateTime(taskArticle.getArticleUpdateTime());
        setArticleSiteId(taskArticle.getArticleSiteId());
        setArticleSkin(taskArticle.getArticleSkin());
        setArticleContent(taskArticle.getArticleContent());
        setArticleDesc(taskArticle.getArticleDesc());
        setArticleHit(taskArticle.getArticleHit());
        setArticleInHomepage(taskArticle.getArticleInHomepage());
        setArticleOrder(taskArticle.getArticleOrder());
        setArticleStatus(taskArticle.getArticleStatus());
        setArticleThumb(taskArticle.getArticleThumb());
        setArticleType(taskArticle.getArticleType());
        setArticleTitle(taskArticle.getArticleTitle());
        setArticleUrl(taskArticle.getArticleUrl());
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
        return article;
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

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }


}
