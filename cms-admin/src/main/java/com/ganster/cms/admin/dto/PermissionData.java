package com.ganster.cms.admin.dto;

import java.util.List;

public class PermissionData {

    private List<String> permissionName;
    private Integer groupId;
    private Integer siteId;
    private Integer categoryId;
    private Integer moudleId;

    public PermissionData(List<String> permissionName, Integer groupId, Integer siteId, Integer categoryId, Integer moudleId, Long articleIntro) {
        this.permissionName = permissionName;
        this.groupId = groupId;
        this.siteId = siteId;
        this.categoryId = categoryId;
        this.moudleId = moudleId;
        this.articleIntro = articleIntro;
    }

    private Long articleIntro;

    public Long getArticleIntro() {
        return articleIntro;
    }

    public void setArticleIntro(Long articleIntro) {
        this.articleIntro = articleIntro;
    }



    public PermissionData() {
    }

    public Integer getMoudleId() {
        return moudleId;
    }

    public void setMoudleId(Integer moudleId) {
        this.moudleId = moudleId;
    }

    public List<String> getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(List<String> permissionName) {
        this.permissionName = permissionName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
