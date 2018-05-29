package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryExample;

public interface CategoryService extends BaseService<Category, CategoryExample> {
    CategoryTree toTree(Category category);

    /**
     * 删除该栏目和与栏目相关联的东西
     */
    void deleteCategory(Integer categoryId);

    /**
     * 批量删除栏目信息，包括权限表的信息
     */
    void deleteBatchCategory(String categoryIdStr);
}
