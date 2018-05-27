package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.common.pojo.Permission;
import com.gangster.cms.common.pojo.PermissionExample;
import com.gangster.cms.common.pojo.Site;

import java.util.List;
import java.util.Set;

public interface PermissionService extends BaseService<Permission,PermissionExample> {
}
