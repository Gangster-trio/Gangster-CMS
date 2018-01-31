package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.GroupPermissionMapper;
import com.ganster.cms.core.dao.mapper.PermissionMapper;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper,Permission,PermissionExample> implements PermissionService {
    @Autowired
    GroupPermissionMapper groupPermissionMapper;
    @Autowired
    GroupService groupService;

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
            example.or().andGroupIdEqualTo(group.getGroupId());
            List<GroupPermission> groupPermissionList = groupPermissionMapper.selectByExample(gpExample);
            if (groupPermissionList == null) {
                return new ArrayList<>();
            }
            for (GroupPermission gp : groupPermissionList) {
                PidList.add(gp.getPermissionId());
            }
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
    public Boolean hasPermission(Integer userId, String pName) throws GroupNotFountException {
        List<Permission> permissionList = selectByUserId(userId);
        for (Permission p:permissionList){
            if (p.getPermissionName().equals(pName))
                return true;
        }
        return false;
    }
}
