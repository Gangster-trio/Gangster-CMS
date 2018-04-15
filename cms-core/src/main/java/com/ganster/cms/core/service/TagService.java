package com.ganster.cms.core.service;


import com.ganster.cms.core.base.BaseService;
import com.gangster.cms.common.pojo.Tag;
import com.gangster.cms.common.pojo.TagExample;

import java.util.List;

public interface TagService extends BaseService<Tag, TagExample> {
    List<Tag> selectByArticleId(Integer id);

    /**
     * 插入关联表信息
     *
     * @param articleId
     * @param tagId
     * @return
     */
    int insertTagAndArticle(Integer articleId, Integer tagId);

}
