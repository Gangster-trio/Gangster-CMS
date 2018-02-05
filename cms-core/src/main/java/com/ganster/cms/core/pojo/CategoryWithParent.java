package com.ganster.cms.core.pojo;

/**
 * Create by Yoke on 2018/2/1
 */
public class CategoryWithParent extends Category {
    private String parentName;

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
                , category.getCategoryInHomepage()
        );
        this.parentName = parentName;
    }
}
