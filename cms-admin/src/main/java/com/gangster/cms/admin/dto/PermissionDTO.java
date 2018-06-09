package com.gangster.cms.admin.dto;

/**
 * DTO 所需要查找到的权限格式
 */
public class PermissionDTO {
    private Integer permissionId;
    private String permissionName;
    private String permissionDesc;

    public PermissionDTO(Integer permissionId, String permissionName, String permissionDesc) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.permissionDesc = permissionDesc;
    }

    public PermissionDTO() {
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
}
