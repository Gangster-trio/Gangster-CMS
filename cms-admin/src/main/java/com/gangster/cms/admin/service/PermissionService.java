package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.common.pojo.Permission;
import com.gangster.cms.common.pojo.PermissionExample;
import com.gangster.cms.common.pojo.Site;

import java.util.List;
import java.util.Set;

public interface PermissionService extends BaseService<Permission,PermissionExample> {

    List<Permission> selectByUserId(Integer id);

    Set<Permission> selectByGroupId(Integer id);

    Set<Permission> selectByGroupName(String name);

    void deleteUserPermission(Integer userId, Integer sid, Integer cid, String pName) throws UserNotFoundException;

    void deleteGroupPermission(Integer groupId, Integer sid, Integer cid, String pName);

    Boolean hasCategoryPermission(Integer uid, Integer sid, Integer cid, String pName);

    Boolean hasModulePermission(Integer uid, Integer sid, Integer moduleId, String pName);

    Boolean hasSitePermission(Integer uid, Integer sid);

    void addCategoryPermissionToUser(Integer uid, Integer sid, Integer cid, String pName) throws UserNotFoundException;

    void addModulePermissionToUser(Integer uid, Integer sid, Integer moduleId, String pName) throws UserNotFoundException;

    void addCategoryPermissionToGroup(Integer gid, Integer sid, Integer cid, String pName);

    void addModulePermissionToGroup(Integer gid, Integer sid, Integer moduleId, String pName);

    void addSitePermissionToGroup(Integer sid,Integer gid);

    void addUserToSite(Integer uid, Integer sid) throws UserNotFoundException;

    List<Site> findAllUserSite(Integer uid) ;

    void deleteUserFromSite(Integer uid, Integer sid) throws UserNotFoundException;

    /**
     * delete permission and permission-group map
     * (instead with deleteByPrimaryKey)
     *
     * @param pid permission ID
     */
    int deletePermission(Integer pid);
}
