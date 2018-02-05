package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.ArticleMapper;
import com.ganster.cms.core.dao.mapper.TagArticleMapper;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article, ArticleExample> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    TagService tagService;
    @Autowired
    TagArticleMapper tagArticleMapper;

    private List<Integer> selectArticlesIdByTagName(String tag) {
        TagExample tagExample = new TagExample();
        tagExample.createCriteria().andTagNameEqualTo(tag);
        List<Tag> tags = tagService.selectByExample(tagExample);
        if (tags == null || tags.size() == 0) {
            return new ArrayList<>();
        }
        TagArticleExample tagArticleExample = new TagArticleExample();
        tagArticleExample.createCriteria().andTagIdEqualTo(tags.get(0).getTagId());
        List<TagArticle> tagArticleList = tagArticleMapper.selectByExample(tagArticleExample);
        if (tagArticleList == null || tagArticleList.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for (TagArticle aTagArticleList : tagArticleList) {
            ids.add(aTagArticleList.getArticleId());
        }
        return ids;
    }

    public List<Article> selectByTagName(String tag) {
        List<Integer> ids = selectArticlesIdByTagName(tag);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleIdIn(ids);
        return selectByExample(articleExample);
    }

    public List<Article> selectByTagNameWithBLOBs(String tag) {
        List<Integer> ids = selectArticlesIdByTagName(tag);
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andArticleIdIn(ids);
        return selectByExampleWithBLOBs(articleExample);
    }

    @Override
    @Transactional
    public int insertWithTag(Article article, String tag) {
        int ret = insert(article);
        insertTagArticle(article, tag);
        return ret;
    }

    @Override
    @Transactional
    public int insertSelectiveWithTag(Article article, String tag) {
        int ret = insertSelective(article);
        insertTagArticle(article, tag);
        return ret;
    }

    private void insertTagArticle(Article article, String tag) {
        TagExample tagExample = new TagExample();
        tagExample.or().andTagNameEqualTo(tag);
        List<Tag> tags = tagService.selectByExample(tagExample);
        if (tags.size() != 0) {
            //tag already exist
            tagExample.clear();
            TagArticle tagArticle = new TagArticle();
            tagArticle.setArticleId(article.getArticleId());
            tagArticle.setTagId(tags.get(0).getTagId());
            tagArticleMapper.insert(tagArticle);
        } else {
            //tag not exist
            Tag newTag = new Tag();
            newTag.setTagCreateTime(new Date());
            newTag.setTagName(tag);
            tagService.insert(newTag);
            TagArticle tagArticle = new TagArticle(newTag.getTagId(), article.getArticleId());
            tagArticleMapper.insert(tagArticle);
        }
    }

    public List<Article> selectArticleByCategoryId(Integer id) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(id);
        return articleMapper.selectByExample(articleExample);
    }

    public int deleteArticleWithTags(Integer articleId) {
        TagArticleExample tagArticleExample = new TagArticleExample();
        tagArticleExample.or().andArticleIdEqualTo(articleId);
        List<TagArticle> list = tagArticleMapper.selectByExample(tagArticleExample);   //得到要删除文章的所有标签
        tagArticleMapper.deleteByExample(tagArticleExample);   //删除中间表
        for (TagArticle tagArticle : list) {
            TagExample tagExample = new TagExample();
            tagExample.or().andTagIdEqualTo(tagArticle.getTagId());
            long count = tagService.countByExample(tagExample);
            if (count == 1) {
                tagService.deleteByPrimaryKey(tagArticle.getTagId());
            }
        }
        return articleMapper.deleteByPrimaryKey(articleId);
    }
}
