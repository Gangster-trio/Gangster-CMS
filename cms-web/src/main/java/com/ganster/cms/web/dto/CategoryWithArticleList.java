package com.ganster.cms.web.dto;

import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.Category;

import java.util.List;
import java.util.Objects;

public class CategoryWithArticleList extends Category {

    private List<Article> articleList;

    public CategoryWithArticleList(Category category, List<Article> articleList) {
        super(category.getCategoryId()
                , category.getCategoryTitle()
                , category.getCategoryCreateTime()
                , category.getCategoryUpdateTime()
                , category.getCategoryParentId()
                , category.getCategoryLevel()
                , category.getCategorySiteId()
                , category.getCategoryStatus()
                , category.getCategoryDesc()
                , category.getCategoryOrder()
                , category.getCategorySkin()
                , category.getCategoryType()
                , category.getCategoryInHomepage()
        );
        this.articleList = articleList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryWithArticleList)) return false;
        if (!super.equals(o)) return false;
        CategoryWithArticleList that = (CategoryWithArticleList) o;
        return Objects.equals(articleList, that.articleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), articleList);
    }

    @Override
    public String toString() {
        return super.toString() +
                "{articleList=" + articleList +
                '}';
    }
}
