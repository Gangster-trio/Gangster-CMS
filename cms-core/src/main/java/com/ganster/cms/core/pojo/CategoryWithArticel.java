package com.ganster.cms.core.pojo;

import java.util.Date;

/**
 * Create by Yoke on 2018/2/1
 */
public class CategoryWithArticel extends Article {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryWithArticel(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, String categoryName) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin);
        this.categoryName = categoryName;
    }

    public CategoryWithArticel(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, String articleContent, String categoryName) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin, articleContent);
        this.categoryName = categoryName;
    }

    public CategoryWithArticel(String categoryName, Article article) {
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
    }
}
