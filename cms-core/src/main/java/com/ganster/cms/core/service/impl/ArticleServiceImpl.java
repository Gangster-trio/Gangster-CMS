package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.ArticleMapper;
import com.ganster.cms.core.dao.mapper.TagArticleMapper;
import com.ganster.cms.core.dao.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper,Article,ArticleExample> implements ArticleService {

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
        insertTagArticle(article,tag);
        return ret;
    }

    @Override
    @Transactional
    public int insertSelectiveWithTag(Article article, String tag) {
        int ret = insertSelective(article);
        insertTagArticle(article,tag);
        return ret;
    }

    private void insertTagArticle(Article article,String tag){
        TagExample tagExample = new TagExample();
        tagExample.or().andTagNameEqualTo(tag);
        List<Tag> tags = tagService.selectByExample(tagExample);
        if (tags.size() != 0) {
            //tag already exist
            tagExample.clear();
            TagArticle tagArticle = new TagArticle(tags.get(0).getTagId(), article.getArticleId());
            tagArticleMapper.insert(tagArticle);
        } else {
            //tag not exist
            Tag newTag = new Tag(tag, new Date());
            tagService.insert(newTag);
            TagArticle tagArticle = new TagArticle(newTag.getTagId(), article.getArticleId());
            tagArticleMapper.insert(tagArticle);
        }
    }
}
