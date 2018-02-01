package com.ganster.cms.core.pojo;

import java.util.Date;

/**
 * Create by Yoke on 2018/2/1
 */
public class CategoryWithParent extends Category {
    private String parentName;

    public CategoryWithParent(Integer categoryId, String categoryTitle, Date categoryCreateTime, Date categoryUpdateTime, Integer categoryParentId, Integer categoryLevel, Integer categorySiteId, Integer categoryStatus, String categoryDesc, Integer categoryOrder, String categorySkin, String categoryType, String parentName) {
        super(categoryId, categoryTitle, categoryCreateTime, categoryUpdateTime, categoryParentId, categoryLevel, categorySiteId, categoryStatus, categoryDesc, categoryOrder, categorySkin, categoryType);
        this.parentName = parentName;
    }

    public CategoryWithParent(String parentName) {
        this.parentName = parentName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public CategoryWithParent(String parentName, Category category) {
        super(category.getCategoryId()
                ,category.getCategoryTitle()
                ,category.getCategoryCreateTime()
                ,category.getCategoryUpdateTime()
                ,category.getCategoryParentId()
                ,category.getCategoryLevel()
                ,category.getCategorySiteId()
                ,category.getCategoryStatus()
                ,category.getCategoryDesc()
                ,category.getCategoryOrder()
                ,category.getCategorySkin()
                ,category.getCategoryType()
        );
        this.parentName = parentName;
    }
}
