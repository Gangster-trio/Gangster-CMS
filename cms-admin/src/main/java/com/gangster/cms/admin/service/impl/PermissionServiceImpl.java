package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.exception.GroupNotFountException;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.admin.util.PermissionUtil;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.GroupPermissionMapper;
import com.gangster.cms.dao.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission, PermissionExample> implements PermissionService {
    @Autowired
    private
    GroupPermissionMapper groupPermissionMapper;

    @Autowired
    private
    SiteService siteService;

    @Autowired
    private
    GroupService groupService;

    @Autowired
    private
    UserService userService;


    @Override
    public List<Permission> selectByUserId(Integer id) {
        List<Group> groupList = groupService.selectByUserId(id);
        if (groupList.isEmpty()) {
            Group g = new Group();
            g.setGroupName(userService.selectByPrimaryKey(id).getUserName());
            g.setGroupDesc("用户 " + id + "(id) 的默认组");
            groupService.insert(g);
            try {
                groupService.addUserToGroup(id, g.getGroupId());
            } catch (UserNotFoundException | GroupNotFountException e) {
                e.printStackTrace();
            }
            return Collections.emptyList();
        }
        return groupList.stream()
                .flatMap(e -> selectByGroupId(e.getGroupId()).stream())
                .collect(Collectors.toList());
    }

    private Set<Permission> selectByGroupExample(GroupExample example) {
        List<Group> groupList = groupService.selectByExample(example);

        //new

        List<Integer> pidSet = groupList.stream().flatMap(group -> {
            GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
//            groupPermissionExample.clear();
            groupPermissionExample.or().andGroupIdEqualTo(group.getGroupId());
            return groupPermissionMapper.selectByExample(groupPermissionExample)
                    .stream()
                    .map(GroupPermission::getPermissionId);
        })
                .distinct()
                .collect(Collectors.toList());
        PermissionExample permissionExample = new PermissionExample();
        if (pidSet.isEmpty())
            return Collections.emptySet();
        permissionExample.or().andPermissionIdIn(pidSet);
        return new HashSet<>(selectByExample(permissionExample));
        //old
        /*
        List<Integer> pidList = new ArrayList<>();
        for (Group group : groupList) {
            GroupPermissionExample gpExample = new GroupPermissionExample();
            gpExample.or().andGroupIdEqualTo(group.getGroupId());
            List<GroupPermission> groupPermissionList = groupPermissionMapper.selectByExample(gpExample);
            for (GroupPermission gp : groupPermissionList) {
                pidList.add(gp.getPermissionId());
            }
        }
        if (pidList.isEmpty()) {
            return Collections.emptySet();
        }
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andPermissionIdIn(pidList);
        return new HashSet<>(selectByExample(permissionExample));
        */
    }

    @Override
    public Set<Permission> selectByGroupId(Integer id) {
        GroupExample example = new GroupExample();
        example.or().andGroupIdEqualTo(id);
        return selectByGroupExample(example);
    }

    @Override
    public Set<Permission> selectByGroupName(String name) {
        GroupExample example = new GroupExample();
        example.or().andGroupNameEqualTo(name);
        return selectByGroupExample(example);
    }

    @Override
    @Transactional
    public void deleteUserPermission(Integer userId, Integer sid, Integer cid, String pName) throws UserNotFoundException {
        deleteUserPermission(userId, PermissionUtil.formatCategoryPermissionName(sid, cid, pName));
    }

    @Override
    @Transactional
    public void deleteGroupPermission(Integer groupId, Integer sid, Integer cid, String pName) {
        deletePermissionFromGroup(groupId, PermissionUtil.formatCategoryPermissionName(sid, cid, pName));
    }

    private Boolean hasPermission(Integer userId, String pName) {
        List<Permission> permissionList = selectByUserId(userId);
        for (Permission p : permissionList) {
            if (p.getPermissionName().equals(pName))
                return true;
        }
        return false;
    }

    private void deleteUserPermission(Integer userId, String pName) throws UserNotFoundException {
        Group group = groupService.findUserOwnGroup(userId);
        deletePermissionFromGroup(group.getGroupId(), pName);
    }

    private void deletePermissionFromGroup(Integer groupId, String pName) {
        Group group = groupService.selectByPrimaryKey(groupId);
        if (group == null) {
            return;
        }

        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or()
                .andGroupIdEqualTo(groupId);

        //该组所有权限的ID
        List<Integer> pidList = groupPermissionMapper.selectByExample(groupPermissionExample)
                .stream()
                .map(GroupPermission::getPermissionId)
                .collect(Collectors.toList());

        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andPermissionNameEqualTo(pName);

        //需要删除的权限ID
        List<Integer> delList = selectByExample(permissionExample).stream()
                .map(Permission::getPermissionId)
                .filter(pidList::contains)
                .collect(Collectors.toList());

        if (delList.isEmpty()) {
            return;
        }

        //删除 group-permission 中间表
        groupPermissionExample.clear();
        groupPermissionExample.or().andPermissionIdIn(delList);
        groupPermissionMapper.deleteByExample(groupPermissionExample);
        //删除权限
        permissionExample.clear();
        permissionExample.or().andPermissionIdIn(delList);
        deleteByExample(permissionExample);
    }

    @Override
    public Boolean hasCategoryPermission(Integer uid, Integer sid, Integer cid, String pName) {
        String permissionName = PermissionUtil.formatCategoryPermissionName(sid, cid, pName);
        return hasPermission(uid, permissionName);
    }

    @Override
    public Boolean hasModulePermission(Integer uid, Integer sid, Integer moduleId, String pName) {
        String permissionName = PermissionUtil.formatModulePermissionName(sid, moduleId, pName);
        return hasPermission(uid, permissionName);
    }

    @Override
    @Transactional
    public void addCategoryPermissionToUser(Integer uid, Integer sid, Integer cid, String pName) throws UserNotFoundException {
        Group group = groupService.findUserOwnGroup(uid);
        addCategoryPermissionToGroup(group.getGroupId(), sid, cid, pName);
    }

    @Override
    public void addModulePermissionToUser(Integer uid, Integer sid, Integer moduleId, String pName) throws UserNotFoundException {
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
        Permission permission = new Permission();
        permission.setPermissionName(PermissionUtil.formatSitePermissionName(sid));
        insert(permission);

        GroupPermission groupPermission = new GroupPermission();
        groupPermission.setGroupId(gid);
        groupPermission.setPermissionId(permission.getPermissionId());
        groupPermissionMapper.insert(groupPermission);
    }

    @Override
    @Transactional
    public void addUserToSite(Integer uid, Integer sid) throws UserNotFoundException {
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
    public Boolean hasSitePermission(Integer uid, Integer sid) {
        return hasPermission(uid, PermissionUtil.formatSitePermissionName(sid));
    }

    @Override
    public List<Site> findAllUserSite(Integer uid) {
        SiteExample siteExample = new SiteExample();
        return siteService.selectByExample(siteExample)
                .stream()
                .filter(s -> hasSitePermission(uid, s.getSiteId()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUserFromSite(Integer uid, Integer sid) throws UserNotFoundException {
        deleteUserPermission(uid, sid.toString());
    }

    @Override
    @Transactional
    public int deletePermission(Integer pid) {
        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or().andPermissionIdEqualTo(pid);
        groupPermissionMapper.deleteByExample(groupPermissionExample);

        return super.deleteByPrimaryKey(pid);
    }
}
