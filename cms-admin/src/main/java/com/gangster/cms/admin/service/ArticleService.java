package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.common.pojo.WebFile;

import java.util.List;

public interface ArticleService extends BaseService<Article, ArticleExample> {
    List<Article> selectByTagName(String tag);

    List<Article> selectByTagNameWithBLOBs(String tag);

    List<Article> selectArticleByCategoryId(Integer id);

    List<Article> selectArticleByCategoryId(Integer cid, String sort);

    List<Article> selectArticleByReleaseStatus(Boolean releaseStatus);

    /**
     * 插入文章,对应标签和文件表
     */
    void insertWithTagAndFile(Article article, List<String> tagNameList, List<WebFile> files);

    /**
     * 更新文章，包括标签和文件
     */
    void updateSelectWithTagAndFile(Integer articleId, Article article, List<String> tagNameList, List<WebFile> files);

    /**
     * 根据文章id删除文章。同时会删除中间表，以及tag表 如果只有tag表只关联该文章，就删除tag表，如果有多个，不删
     *
     * @param articleId 文章id
     */

    void deleteArticleWithTagAndFile(Integer articleId);
}
