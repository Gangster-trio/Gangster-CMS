package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;

import java.util.List;

public interface ArticleService extends BaseService<Article, ArticleExample> {
    List<Article> selectByTagName(String tag);

    List<Article> selectByTagNameWithBLOBs(String tag);

    List<Article> selectArticleByCategoryId(Integer id);

    List<Article> selectArticleByCategoryId(Integer cid, String sort);

    int insertWithTag(Article article, List<String> tagNameList);

    int insertSelectiveWithTag(Article article, List<String> tagNameList);

    /**
     * 根据文章id删除文章。同时会删除中间表，以及tag表 如果只有tag表只关联该文章，就删除tag表，如果有多个，不删
     *
     * @param articleId 文章id
     * @return
     */
    int deleteArticleWithTags(Integer articleId);
}
