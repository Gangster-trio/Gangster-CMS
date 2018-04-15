package com.ganster.cms.core.service.impl;


import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.mapper.TagArticleMapper;
import com.ganster.cms.core.mapper.TagMapper;
import com.gangster.cms.common.pojo.Tag;
import com.gangster.cms.common.pojo.TagArticle;
import com.gangster.cms.common.pojo.TagArticleExample;
import com.gangster.cms.common.pojo.TagExample;
import com.ganster.cms.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends BaseServiceImpl<TagMapper, Tag, TagExample> implements TagService {
    @Autowired
    TagArticleMapper tagArticleMapper;
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> selectByArticleId(Integer id) {
        TagArticleExample tagArticleExample = new TagArticleExample();
        tagArticleExample.or().andArticleIdEqualTo(id);
        List<TagArticle> tagArticleList = tagArticleMapper.selectByExample(tagArticleExample);

        List<Tag> tags = new ArrayList<>();

        for (TagArticle tagArticle : tagArticleList) {
            tags.add(selectByPrimaryKey(tagArticle.getTagId()));
        }

        return tags;
    }

    @Override
    public int insertTagAndArticle(Integer articleId, Integer tagId) {
        TagArticle tagArticle = new TagArticle();
        tagArticle.setArticleId(articleId);
        tagArticle.setTagId(tagId);
        return tagArticleMapper.insert(tagArticle);
    }
}