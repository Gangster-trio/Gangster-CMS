package com.gangster.cms.admin.dto;

import com.gangster.cms.common.pojo.Article;

// TODO: 2018/4/15 改为封装
public class ArticleDTO extends Article {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ArticleDTO() {
        super();
    }

    public ArticleDTO(Article article) {
        setArticleId(article.getArticleId());
        setArticleAuthor(article.getArticleAuthor());
        setArticleCategoryId(article.getArticleCategoryId());
        setArticleCreateTime(article.getArticleCreateTime());
        setArticleUpdateTime(article.getArticleUpdateTime());
        setArticleSiteId(article.getArticleSiteId());
        setArticleSkin(article.getArticleSkin());
        setArticleContent(article.getArticleContent());
        setArticleDesc(article.getArticleDesc());
        setArticleHit(article.getArticleHit());
        setArticleInHomepage(article.getArticleInHomepage());
        setArticleOrder(article.getArticleOrder());
        setArticleStatus(article.getArticleStatus());
        setArticleThumb(article.getArticleThumb());
        setArticleType(article.getArticleType());
        setArticleTitle(article.getArticleTitle());
        setArticleUrl(article.getArticleUrl());
    }

    //不可以向下转型
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
}
