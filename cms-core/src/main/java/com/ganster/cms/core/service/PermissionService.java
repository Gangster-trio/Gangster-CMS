package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.pojo.PermissionExample;

import java.util.List;

public interface PermissionService extends BaseService<Permission,PermissionExample> {

    List<Permission> selectByUserId(Integer id) throws GroupNotFountException;

    List<Permission> selectByGroupId(Integer id) throws GroupNotFountException;

    List<Permission> selectByGroupName(String name) throws GroupNotFountException;
}
