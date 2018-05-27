package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.common.pojo.Permission;
import com.gangster.cms.common.pojo.PermissionExample;
import com.gangster.cms.dao.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission, PermissionExample> implements PermissionService {
}
