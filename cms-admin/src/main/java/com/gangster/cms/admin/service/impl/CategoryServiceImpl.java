package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.common.pojo.CategoryTree;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category, CategoryExample> implements CategoryService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private GroupService groupService;
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
    public void deleteCategoryInfo(Integer siteId, Integer categoryId) {
        int count = 0;
        // 权限无法级联删除
        List<Group> groups = groupService.selectByExample(new GroupExample());
        groups.forEach(g -> {
            permissionService.deleteGroupPermission(g.getGroupId(), siteId, categoryId, CmsConst.PERMISSION_READ);
            permissionService.deleteGroupPermission(g.getGroupId(), siteId, categoryId, CmsConst.PERMISSION_WRITE);
        });
        // 数据库级联删除，以下代码可忽略
        articleService
                .selectArticleByCategoryId(categoryId)
                .forEach(a -> articleService.deleteArticleWithTagAndFile(a.getArticleId()));

        categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public void deleteBatchCategoryInfo(String categoryIdStr) {
        String[] categoryIds = categoryIdStr.split(",");
        int count = 0;

        // 得到批处理栏目集合的id
        List<Integer> categoryIdList = new ArrayList<Integer>();
        for (String categoryId : categoryIds) {
            categoryIdList.add(Integer.parseInt(categoryId));
        }
        // 得到操作的网站的id
        Integer siteId = selectByPrimaryKey(categoryIdList.get(0)).getCategorySiteId();
        try {
            for (Integer categoryId : categoryIdList) {
                deleteCategoryInfo(siteId, categoryId);
            }
        } catch (Exception e) {
            LOGGER.info("批量删除栏目时发生错误");
        }
    }
}
