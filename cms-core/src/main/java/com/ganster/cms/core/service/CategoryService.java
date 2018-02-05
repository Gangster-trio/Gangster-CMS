package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.CategoryExample;
import com.ganster.cms.core.pojo.CategoryTree;

import java.util.List;

public interface CategoryService extends BaseService<Category,CategoryExample> {
     CategoryTree toTree(Category category);
}
