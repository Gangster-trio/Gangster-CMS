package com.ganster.cms.core.dao.pojo;

public class TagArticle {
    private Integer tagId;

    private Integer articleId;

    public TagArticle(Integer tagId, Integer articleId) {
        this.tagId = tagId;
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "TagArticle{" +
                "tagId=" + tagId +
                ", articleId=" + articleId +
                '}';
    }
}