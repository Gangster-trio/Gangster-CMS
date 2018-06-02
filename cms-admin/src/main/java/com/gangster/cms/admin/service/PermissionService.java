package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.Permission;
import com.gangster.cms.common.pojo.PermissionExample;

public interface PermissionService extends BaseService<Permission,PermissionExample> {
    boolean hasPermission(Integer uid,Integer sid,Integer moduleId);
    boolean hasPermission(Integer uid,Integer sid,String moduleName);
}
