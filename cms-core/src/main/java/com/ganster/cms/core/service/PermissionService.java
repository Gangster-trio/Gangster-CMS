package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.PermissionNotFoundException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.pojo.PermissionExample;
import com.ganster.cms.core.pojo.Site;

import java.util.List;

public interface PermissionService extends BaseService<Permission,PermissionExample> {

    List<Permission> selectByUserId(Integer id) throws GroupNotFountException;

    List<Permission> selectByGroupId(Integer id) throws GroupNotFountException;

    List<Permission> selectByGroupName(String name) throws GroupNotFountException;

    void deleteUserPermission(Integer userId, Integer sid, Integer cid, String pName) throws UserNotFoundException, GroupNotFountException, PermissionNotFoundException;

    void deleteGroupPermission(Integer groupId, Integer sid, Integer cid, String pName) throws GroupNotFountException;

    Boolean hasPermission(Integer uid, Integer sid, Integer cid, String pName);

    void addPermissionToUser(Integer uid, Integer sid, Integer cid, String pName) throws GroupNotFountException, UserNotFoundException;

    void addPermissionToGroup(Integer gid, Integer sid, Integer cid, String pName);

    void addUserToSite(Integer uid, Integer sid) throws UserNotFoundException, GroupNotFountException;

    Boolean hasPermissionToSite(Integer uid, Integer sid) throws GroupNotFountException;

    List<Site> findAllUserSite(Integer uid) throws GroupNotFountException;

    void deleteUserFromSite(Integer uid, Integer sid) throws GroupNotFountException, UserNotFoundException;

    void deletePermission(Integer pid);
}
