package com.ganster.cms.admin.dto;

import com.ganster.cms.core.pojo.Article;

import java.util.Date;

/**
 * Create by Yoke on 2018/2/1
 */
public class ArticleWithCategoryName extends Article {
    private String categoryName;

    private String tagsName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArticleWithCategoryName(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, String categoryName) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin);
        this.categoryName = categoryName;
    }

    public ArticleWithCategoryName(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, String articleContent, String categoryName) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin, articleContent);
        this.categoryName = categoryName;
    }

    public ArticleWithCategoryName(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, String articleContent, String categoryName, String tagsName) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin, articleContent);
        this.categoryName = categoryName;
        this.tagsName = tagsName;
    }

    public ArticleWithCategoryName(String categoryName, String tagsName) {
        this.categoryName = categoryName;
        this.tagsName = tagsName;
    }

    public ArticleWithCategoryName(String articleTitle, String articleType, String articleAuthor, Integer articleOrder, Integer articleCategoryId, String articleDesc, String articleSkin, String articleContent, String categoryName, String tagsName) {
        super(articleTitle, articleType, articleAuthor, articleOrder, articleCategoryId, articleDesc, articleSkin, articleContent);
        this.categoryName = categoryName;
        this.tagsName = tagsName;
    }

    public ArticleWithCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArticleWithCategoryName(String articleTitle, String articleType, String articleAuthor, Integer articleOrder, Integer articleCategoryId, String articleDesc, String articleSkin, String articleContent, String categoryName) {
        super(articleTitle, articleType, articleAuthor, articleOrder, articleCategoryId, articleDesc, articleSkin, articleContent);
        this.categoryName = categoryName;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public ArticleWithCategoryName(String categoryName, String tagsName, Article article) {
        super(article.getArticleId()
                , article.getArticleTitle()
                , article.getArticleType()
                , article.getArticleAuthor()
                , article.getArticleUrl()
                , article.getArticleOrder()
                , article.getArticleSiteId()
                , article.getArticleCategoryId()
                , article.getArticleCreateTime()
                , article.getArticleUpdateTime()
                , article.getArticleThumb()
                , article.getArticleHit()
                , article.getArticleDesc()
                , article.getArticleStatus()
                , article.getArticleSkin()
                , article.getArticleContent()
        );
        this.categoryName = categoryName;
        this.tagsName = tagsName;

    }
}
