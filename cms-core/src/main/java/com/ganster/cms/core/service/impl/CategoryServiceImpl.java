package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.CategoryMapper;
import com.ganster.cms.core.dao.pojo.Category;
import com.ganster.cms.core.dao.pojo.CategoryExample;
import com.ganster.cms.core.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper,Category,CategoryExample> implements CategoryService{
}
