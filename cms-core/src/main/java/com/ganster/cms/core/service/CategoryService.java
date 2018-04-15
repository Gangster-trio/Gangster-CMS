package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryExample;
import com.gangster.cms.common.pojo.CategoryTree;

public interface CategoryService extends BaseService<Category, CategoryExample> {
    CategoryTree toTree(Category category);

    /**
     * 删除栏目表信息的同时，删除关联表的信息
     */
    int deleteCategoryInfo(Integer siteId, Integer categoryId, String permission);

    /**
     * 批量删除栏目信息，包括权限表的信息
     */
    int deleteBatchCategoryInfo( String categoryIdStr, String permission);
}
