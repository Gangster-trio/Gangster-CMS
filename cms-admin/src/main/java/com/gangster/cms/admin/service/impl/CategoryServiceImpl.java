package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryExample;
import com.gangster.cms.dao.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category, CategoryExample> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    @Autowired
    private ArticleService articleService;

    @Override
    public CategoryTree toTree(Category category) {
        CategoryTree tree = new CategoryTree();
        int columnId = category.getCategoryId();
        tree.setId(columnId);
        tree.setName(category.getCategoryTitle());
        tree.setSpread(false);
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryParentIdEqualTo(category.getCategoryId()).andCategoryStatusEqualTo(CmsConst.ACCESS);
        //子栏目
        List<Category> list = selectByExample(categoryExample);
        if (list == null) {
            tree.setChildren(null);
            return tree;
        }
        tree.setChildren(list.stream().map(this::toTree).collect(Collectors.toList()));
        return tree;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // 数据库级联删除，以下代码可忽略
        articleService
                .selectArticleByCategoryId(categoryId)
                .forEach(a -> articleService.deleteArticleWithTagAndFile(a.getArticleId()));

        categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public void deleteBatchCategory(String categoryIdStr) {
        String[] categoryIds = categoryIdStr.split(",");
        Stream.of(categoryIds).forEach(e -> deleteCategory(Integer.parseInt(e)));
    }
}
