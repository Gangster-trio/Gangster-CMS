package com.gangster.cms.admin.dto;

import com.gangster.cms.common.pojo.Article;

public class ArticleDTO {
    private Article article;
    private String categoryName;
    private String tags;
    private String files;

    public ArticleDTO(Article article, String categoryName, String tags, String files) {
        this.article = article;
        this.categoryName = categoryName;
        this.tags = tags;
        this.files = files;
    }
    public ArticleDTO(){}
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public ArticleDTO(Article article) {
        this.article = article;
    }
    public ArticleDTO(Article article, String categoryName, String tags) {
        this.article = article;
        this.categoryName = categoryName;
        this.tags = tags;
    }
}
