package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.mapper.CategoryMapper;
import com.ganster.cms.core.mapper.GroupPermissionMapper;
import com.ganster.cms.core.mapper.PermissionMapper;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.util.PermissionUtil;
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
    private PermissionMapper permissionMapper;
    @Autowired
    private GroupPermissionMapper groupPermissionMapper;

    @Override
    public CategoryTree toTree(Category category) {
        CategoryTree tree = new CategoryTree();
        int columnId = category.getCategoryId();
        tree.setId(columnId);
        tree.setName(category.getCategoryTitle());
        tree.setSpread(false);
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryParentIdEqualTo(category.getCategoryId()).andCategoryStatusEqualTo(1);
        //子栏目
        List<Category> list = selectByExample(categoryExample);
        if (list == null || list.isEmpty()) {
            tree.setChildren(null);
            return tree;
        }

        tree.setChildren(list.stream().map(this::toTree).collect(Collectors.toList()));

        return tree;
    }

    @Override
    public int deleteCategoryInfo(Integer siteId, Integer categoryId, String permission) {
        int count = 0;
        // 根据permision名字查询permisison数据表
        String deleteString = PermissionUtil.formatCategoryPermissionName(siteId, categoryId, permission);
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andPermissionNameEqualTo(deleteString);
        Permission p = permissionMapper.selectByExample(permissionExample).get(0);
        // 根据权限id查询组和权限的关联表的信息
        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or().andPermissionIdEqualTo(p.getPermissionId());
        // 删除组和权限表的信息
        count += groupPermissionMapper.deleteByExample(groupPermissionExample);
        // 删除权限表的信息
        count += permissionMapper.deleteByExample(permissionExample);
        if (permission.equals(CmsConst.PERMISSION_WRITE)) {
            count += categoryMapper.deleteByPrimaryKey(categoryId);
        }
        return count;
    }

    @Override
    public int deleteBatchCategoryInfo(String categoryIdStr, String permission) {
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
                count += deleteCategoryInfo(siteId, categoryId, permission);
            }
            if (permission.equals(CmsConst.PERMISSION_WRITE)) {
                CategoryExample categoryExample = new CategoryExample();
                categoryExample.or().andCategoryIdIn(categoryIdList);
                count += categoryMapper.deleteByExample(categoryExample);
            }
        } catch (Exception e) {
            LOGGER.info("批量删除栏目时发生错误");
            return 0;
        }
        return count;
    }
}
