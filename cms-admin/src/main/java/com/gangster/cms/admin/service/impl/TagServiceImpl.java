package com.gangster.cms.admin.service.impl;


import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.TagService;
import com.gangster.cms.common.pojo.Tag;
import com.gangster.cms.common.pojo.TagArticle;
import com.gangster.cms.common.pojo.TagArticleExample;
import com.gangster.cms.common.pojo.TagExample;
import com.gangster.cms.dao.mapper.TagArticleMapper;
import com.gangster.cms.dao.mapper.TagMapper;
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