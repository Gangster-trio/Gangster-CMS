package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.GroupPermissionMapper;
import com.ganster.cms.core.dao.mapper.PermissionMapper;
import com.ganster.cms.core.dao.mapper.UserGroupMapper;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper,Permission,PermissionExample> implements PermissionService {
    @Autowired
    GroupPermissionMapper groupPermissionMapper;

    @Autowired
    SiteService siteService;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Override
    public List<Permission> selectByUserId(Integer id) throws GroupNotFountException {
        List<Group> groupList = groupService.selectByUserId(id);
        if (groupList == null)
            return new ArrayList<>();
        List<Permission> pList = new ArrayList<>();
        for (Group group : groupList) {
            pList.addAll(selectByGroupId(group.getGroupId()));
        }
        return pList;
    }

    private List<Permission> selectByGroupExample(GroupExample example) throws GroupNotFountException {
        List<Group> groupList = groupService.selectByExample(example);
        List<Integer> PidList = new ArrayList<>();
        if (groupList == null || groupList.size() == 0) {
            throw new GroupNotFountException();
        }
        for (Group group : groupList) {
            GroupPermissionExample gpExample = new GroupPermissionExample();
            gpExample.or().andGroupIdEqualTo(group.getGroupId());
            List<GroupPermission> groupPermissionList = groupPermissionMapper.selectByExample(gpExample);
            if (groupPermissionList == null) {
                return new ArrayList<>();
            }
            for (GroupPermission gp : groupPermissionList) {
                PidList.add(gp.getPermissionId());
            }
        }
        if (PidList.size() == 0) {
            return new ArrayList<>();
        }
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andPermissionIdIn(PidList);
        return selectByExample(permissionExample);
    }

    @Override
    public List<Permission> selectByGroupId(Integer id) throws GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupIdEqualTo(id);
        return selectByGroupExample(example);
    }

    @Override
    public List<Permission> selectByGroupName(String name) throws GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupNameEqualTo(name);
        return selectByGroupExample(example);
    }

    @Override
    @Transactional
    public void deleteUserPermission(Integer userId, Integer sid, Integer cid, String pName) throws UserNotFoundException, GroupNotFountException {
        deleteUserPermission(userId, PermissionUtil.formatCategoryPermissionName(sid, cid, pName));
    }

    @Override
    @Transactional
    public void deleteGroupPermission(Integer groupId, Integer sid, Integer cid, String pName) throws GroupNotFountException {
        deleteGroupPermission(groupId, PermissionUtil.formatCategoryPermissionName(sid, cid, pName));
    }

    private Boolean hasPermission(Integer userId, String pName) throws GroupNotFountException {
        List<Permission> permissionList = selectByUserId(userId);
        for (Permission p:permissionList){
            if (p.getPermissionName().equals(pName))
                return true;
        }
        return false;
    }

    private void deleteUserPermission(Integer userId, String pName) throws GroupNotFountException, UserNotFoundException {
        Group group = groupService.findUserOwnGroup(userId);
        deleteGroupPermission(group.getGroupId(), pName);
    }

    private void deleteGroupPermission(Integer groupId, String pName) throws GroupNotFountException {
        Group group = groupService.selectByPrimaryKey(groupId);
        if (group == null) {
            throw new GroupNotFountException(groupId.toString());
        }
        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or().andGroupIdEqualTo(groupId);
        List<GroupPermission> groupPermissionList = groupPermissionMapper.selectByExample(groupPermissionExample);
        if (groupPermissionList == null || groupPermissionList.isEmpty()) {
            return;
        }
        for (GroupPermission groupPermission : groupPermissionList) {
            PermissionExample permissionExample = new PermissionExample();
            permissionExample.or().andPermissionIdEqualTo(groupPermission.getPermissionId()).andPermissionNameEqualTo(pName);
            deleteByExample(permissionExample);
        }
    }

    @Override
    public Boolean hasCategoryPermission(Integer uid, Integer sid, Integer cid, String pName) {
        String permissionName = PermissionUtil.formatCategoryPermissionName(sid, cid, pName);
        try {
            return hasPermission(uid, permissionName);
        } catch (GroupNotFountException e) {
            return false;
        }
    }

    @Override
    public Boolean hasModulePermission(Integer uid, Integer sid, Integer moduleId, String pName) {
        String permissionName = PermissionUtil.formatModulePermissionName(sid,moduleId,pName);
        try {
            return hasPermission(uid, permissionName);
        } catch (GroupNotFountException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void addCategoryPermissionToUser(Integer uid, Integer sid, Integer cid, String pName) throws GroupNotFountException, UserNotFoundException {
        Group group = groupService.findUserOwnGroup(uid);
        addCategoryPermissionToGroup(group.getGroupId(), sid, cid, pName);
    }

    @Override
    public void addModulePermissionToUser(Integer uid, Integer sid, Integer moduleId, String pName) throws UserNotFoundException, GroupNotFountException {
        Group group = groupService.findUserOwnGroup(uid);
        addModulePermissionToGroup(group.getGroupId(), sid, moduleId, pName);
    }

    @Override
    @Transactional
    public void addCategoryPermissionToGroup(Integer gid, Integer sid, Integer cid, String pName) {

        Permission permission = new Permission();
        permission.setPermissionName(PermissionUtil.formatCategoryPermissionName(sid, cid, pName));
        insert(permission);

        GroupPermission groupPermission = new GroupPermission();
        groupPermission.setGroupId(gid);
        groupPermission.setPermissionId(permission.getPermissionId());
        groupPermissionMapper.insert(groupPermission);
    }

    @Override
    public void addModulePermissionToGroup(Integer gid, Integer sid, Integer moduleId, String pName) {
        Permission permission = new Permission();
        permission.setPermissionName(PermissionUtil.formatModulePermissionName(sid, moduleId, pName));
        insert(permission);

        GroupPermission groupPermission = new GroupPermission();
        groupPermission.setGroupId(gid);
        groupPermission.setPermissionId(permission.getPermissionId());
        groupPermissionMapper.insert(groupPermission);
    }

    @Override
    public void addSitePermissionToGroup(Integer sid, Integer gid) {
        Permission permission=new Permission();
        permission.setPermissionName(PermissionUtil.formatSitePermissionName(sid));
        insert(permission);

        GroupPermission groupPermission=new GroupPermission();
        groupPermission.setGroupId(gid);
        groupPermission.setPermissionId(permission.getPermissionId());
        groupPermissionMapper.insert(groupPermission);
    }

    @Override
    @Transactional
    public void addUserToSite(Integer uid, Integer sid) throws UserNotFoundException, GroupNotFountException {
        Group group = groupService.findUserOwnGroup(uid);
        Permission permission = new Permission();
        permission.setPermissionName(PermissionUtil.formatSitePermissionName(sid));
        insert(permission);

        GroupPermission groupPermission = new GroupPermission();
        groupPermission.setPermissionId(permission.getPermissionId());
        groupPermission.setGroupId(group.getGroupId());
        groupPermissionMapper.insert(groupPermission);
    }

    @Override
    public Boolean hasSitePermission(Integer uid, Integer sid) throws GroupNotFountException {
        return hasPermission(uid, PermissionUtil.formatSitePermissionName(sid));
    }

    @Override
    public List<Site> findAllUserSite(Integer uid) throws GroupNotFountException {
        SiteExample siteExample = new SiteExample();
        List<Site> allSite = siteService.selectByExample(siteExample);
        if (allSite == null || allSite.isEmpty()) {
            return new ArrayList<>();
        }
        List<Site> userSite = new ArrayList<>();
        for (Site site : allSite) {
            if (hasSitePermission(uid, site.getSiteId())) {
                userSite.add(site);
            }
        }
        return userSite;
    }

    @Override
    @Transactional
    public void deleteUserFromSite(Integer uid, Integer sid) throws GroupNotFountException, UserNotFoundException {
        deleteUserPermission(uid, sid.toString());
    }

    @Override
    @Transactional
    public void deletePermission(Integer pid) {
        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or().andPermissionIdEqualTo(pid);
        groupPermissionMapper.deleteByExample(groupPermissionExample);

        deleteByPrimaryKey(pid);
    }
}
