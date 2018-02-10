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

    Boolean hasCategoryPermission(Integer uid, Integer sid, Integer cid, String pName);

    Boolean hasModulePermission(Integer uid, Integer sid, Integer moduleId, String pName);

    Boolean hasSitePermission(Integer uid, Integer sid) throws GroupNotFountException;

    void addCategoryPermissionToUser(Integer uid, Integer sid, Integer cid, String pName) throws GroupNotFountException, UserNotFoundException;

    void addModulePermissionToUser(Integer uid, Integer sid, Integer moduleId, String pName) throws UserNotFoundException, GroupNotFountException;

    void addCategoryPermissionToGroup(Integer gid, Integer sid, Integer cid, String pName);

    void addModulePermissionToGroup(Integer gid, Integer sid, Integer moduleId, String pName);

    void addSitePermissionToGroup(Integer sid,Integer gid);

    void addUserToSite(Integer uid, Integer sid) throws UserNotFoundException, GroupNotFountException;

    List<Site> findAllUserSite(Integer uid) throws GroupNotFountException;

    void deleteUserFromSite(Integer uid, Integer sid) throws GroupNotFountException, UserNotFoundException;

    /**
     * delete permission and permission-group map
     * (instead with deleteByPrimaryKey)
     *
     * @param pid permission ID
     */
    int deletePermission(Integer pid);
}
