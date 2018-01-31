package com.ganster.cms.core.service;


import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.pojo.Tag;
import com.ganster.cms.core.pojo.TagExample;

import java.util.List;

public interface TagService extends BaseService<Tag,TagExample> {
    List<Tag> selectByArticleId(Integer id);
}
