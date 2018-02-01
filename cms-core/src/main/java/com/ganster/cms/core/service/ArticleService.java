package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;

import java.util.List;

public interface ArticleService extends BaseService<Article, ArticleExample> {
    List<Article> selectByTagName(String tag);

    List<Article> selectByTagNameWithBLOBs(String tag);

    List<Article> selectArticleByCategoryId(Integer id);

    int insertWithTag(Article article, String tag);

    int insertSelectiveWithTag(Article article, String tag);
}
