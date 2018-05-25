package com.gangster.cms.search.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "article")
public class ArticleModel extends Article{
    @Id
    private Integer articleId;

    @Override
    public Integer getArticleId() {
        return articleId;
    }

    @Override
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
