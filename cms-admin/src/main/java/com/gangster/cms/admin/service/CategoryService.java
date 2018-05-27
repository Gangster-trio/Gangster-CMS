package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryExample;

public interface CategoryService extends BaseService<Category, CategoryExample> {
    CategoryTree toTree(Category category);

    /**
     * 删除栏目表信息的同时，删除关联表的信息
     */
    void deleteCategoryInfo(Integer siteId, Integer categoryId);

    /**
     * 批量删除栏目信息，包括权限表的信息
     */
    void deleteBatchCategoryInfo(String categoryIdStr);
}
