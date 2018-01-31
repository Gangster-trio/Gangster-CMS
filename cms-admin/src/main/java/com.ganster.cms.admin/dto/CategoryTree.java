package com.ganster.cms.admin.dto;

import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
public class CategoryTree {
    private Integer id;
    private String name;
    private boolean spread;
    private List<CategoryTree> children;

    public CategoryTree(Integer id, String name, boolean spread, List<CategoryTree> children) {
        this.id = id;
        this.name = name;
        this.spread = spread;
        this.children = children;
    }

    public CategoryTree() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<CategoryTree> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTree> children) {
        this.children = children;
    }
}
