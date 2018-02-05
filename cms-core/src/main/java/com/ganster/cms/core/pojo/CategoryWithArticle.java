package com.ganster.cms.core.pojo;

/**
 * Create by Yoke on 2018/2/1
 */
public class CategoryWithArticle extends Article {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryWithArticle(String categoryName, Article article) {
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
                , article.getArticleInHomepage()
                , article.getArticleContent()
        );
        this.categoryName = categoryName;
    }
}
