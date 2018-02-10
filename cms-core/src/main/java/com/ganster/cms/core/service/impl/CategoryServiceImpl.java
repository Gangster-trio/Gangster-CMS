package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.CategoryMapper;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.CategoryExample;
import com.ganster.cms.core.pojo.CategoryTree;
import com.ganster.cms.core.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category, CategoryExample> implements CategoryService {
    @Override
    public CategoryTree toTree(Category category) {
        CategoryTree tree = new CategoryTree();
        int columnId = category.getCategoryId();
        tree.setId(columnId);
        tree.setName(category.getCategoryTitle());
        tree.setSpread(false);
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryParentIdEqualTo(category.getCategoryId());
        List<Category> list = selectByExample(categoryExample);  //子栏目
        if (list == null || list.isEmpty()) {
            tree.setChildren(null);
            return tree;
        }
        List<CategoryTree> categoryTrees = new ArrayList<>();

        for (Category c : list) {
            CategoryTree categoryTree = toTree(c);
            categoryTrees.add(categoryTree);
        }
        tree.setChildren(categoryTrees);
        return tree;
    }
}
