package com.ganster.cms.core.service.impl;


import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.TagMapper;
import com.ganster.cms.core.dao.pojo.Tag;
import com.ganster.cms.core.dao.pojo.TagExample;
import com.ganster.cms.core.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends BaseServiceImpl<TagMapper,Tag,TagExample> implements TagService{
}
