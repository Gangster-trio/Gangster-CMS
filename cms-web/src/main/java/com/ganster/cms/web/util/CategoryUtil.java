package com.ganster.cms.web.util;

import com.gangster.cms.common.pojo.*;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryUtil {
    public static CategoryTree toTree(Category category) {
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

        tree.setChildren(list.stream().map(CategoryUtil::toTree).collect(Collectors.toList()));

        return tree;
    }
}
