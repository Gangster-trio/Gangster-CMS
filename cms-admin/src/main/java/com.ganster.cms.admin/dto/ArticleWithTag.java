package com.ganster.cms.admin.dto;

import com.ganster.cms.core.pojo.Article;

import java.util.Date;

/**
 * Create by Yoke on 2018/2/3
 */
public class ArticleWithTag extends Article {
    private String tags;

    public ArticleWithTag(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, Boolean inHomepage, String tags) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin, inHomepage);
        this.tags = tags;
    }

    public ArticleWithTag(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, Boolean inHomepage, String articleContent, String tags) {
        super(articleId, articleTitle, articleType, articleAuthor, articleUrl, articleOrder, articleSiteId, articleCategoryId, articleCreateTime, articleUpdateTime, articleThumb, articleHit, articleDesc, articleStatus, articleSkin, inHomepage, articleContent);
        this.tags = tags;
    }

    public ArticleWithTag(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ArticleWithTag() {
        super();
    }

    public ArticleWithTag(String articleTitle, String articleType, String articleAuthor, Integer articleOrder, Integer articleCategoryId, String articleDesc, String articleSkin, String articleContent) {
        this.setArticleTitle(articleTitle);
        this.setArticleType(articleType);
        this.setArticleOrder(articleOrder);
        this.setArticleCategoryId(articleCategoryId);
        this.setArticleDesc(articleDesc);
        this.setArticleSkin(articleSkin);
        this.setArticleContent(articleContent);
    }
}
