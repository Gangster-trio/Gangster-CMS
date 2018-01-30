package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.PermissionMapper;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.pojo.PermissionExample;
import com.ganster.cms.core.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper,Permission,PermissionExample> implements PermissionService {
}
