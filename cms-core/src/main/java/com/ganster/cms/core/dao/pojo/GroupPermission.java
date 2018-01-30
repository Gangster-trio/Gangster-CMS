package com.ganster.cms.core.dao.pojo;

public class GroupPermission {
    private Integer groupId;

    private Integer permissionId;

    public GroupPermission(Integer groupId, Integer permissionId) {
        this.groupId = groupId;
        this.permissionId = permissionId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "GroupPermission{" +
                "groupId=" + groupId +
                ", permissionId=" + permissionId +
                '}';
    }
}