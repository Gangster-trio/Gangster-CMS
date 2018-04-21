package com.gangster.cms.admin.dto;


import com.gangster.cms.common.pojo.Group;

import java.util.List;

public class GroupWithPermission  extends Group {

    private List<String> permissionNameList;


    public GroupWithPermission(Group group, List<String> permissionNameList) {
        super(group.getGroupId(), group.getGroupName(), group.getGroupDesc());
        this.permissionNameList = permissionNameList;
    }

    public GroupWithPermission(List<String> permissionNameList) {
        this.permissionNameList = permissionNameList;
    }

    public List<String> getPermissionNameList() {
        return permissionNameList;
    }

    public void setPermissionNameList(List<String> permissionNameList) {
        this.permissionNameList = permissionNameList;
    }
}
