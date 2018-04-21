package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.exception.GroupNotFountException;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.GroupMapper;
import com.gangster.cms.dao.mapper.GroupPermissionMapper;
import com.gangster.cms.dao.mapper.UserGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, Group, GroupExample> implements GroupService {
    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;
    @Autowired
    GroupPermissionMapper groupPermissionMapper;

    @Override
    public List<Group> selectByUserId(Integer id) {
        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andUserIdEqualTo(id);
        List<UserGroup> userGroupList = userGroupMapper.selectByExample(userGroupExample);
        List<Group> groupList = new ArrayList<>();
        for (UserGroup ug : userGroupList) {
            groupList.add(selectByPrimaryKey(ug.getGroupId()));
        }
        return groupList;
    }

    @Override
    public List<User> selectUserByGroupId(Integer id) throws GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupIdEqualTo(id);
        return selectUserByGroupExample(example);
    }

    @Override
    public List<User> selectUserByGroupName(String name) throws GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupNameEqualTo(name);
        return selectUserByGroupExample(example);
    }

    @Override
    public Group findUserOwnGroup(Integer userId) throws UserNotFoundException {
        User user = userService.selectByPrimaryKey(userId);
        if (user == null) {
            throw new UserNotFoundException(userId.toString());
        }

        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andUserIdEqualTo(userId);
        List<UserGroup> userGroupList = userGroupMapper.selectByExample(userGroupExample);
        if (userGroupList.isEmpty()) {
            Group group = new Group();
            group.setGroupName(user.getUserName());
            group.setGroupDesc("用户" + user.getUserName() + "的默认组");
            insert(group);
            try {
                this.addUserToGroup(userId, group.getGroupId());
            } catch (GroupNotFountException e) {
                //一般不会发生
                e.printStackTrace();
            }
            return group;
        }
        //user's group
        for (UserGroup userGroup : userGroupList) {
            Group group = selectByPrimaryKey(userGroup.getGroupId());
            if (group == null) {
                continue;
            }
            if (group.getGroupName().equals(user.getUserName())) {
                return group;
            }
        }
        //不应该发生
        return null;
    }

    @Override
    public void addUserToGroup(Integer Uid, Integer Gid) throws UserNotFoundException, GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupIdEqualTo(Gid);
        addUserToGroup(Uid, example);
    }

    @Override
    public void addUserToGroup(Integer Uid, String gName) throws UserNotFoundException, GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupNameEqualTo(gName);
        addUserToGroup(Uid, example);
    }

    @Override
    public void removeUserFromGroup(Integer Uid, String gName) throws UserNotFoundException, GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupNameEqualTo(gName);
        removeUserFromGroup(Uid, example);
    }

    @Override
    public void removeUserFromGroup(Integer Uid, Integer Gid) throws UserNotFoundException, GroupNotFountException {
        GroupExample example = new GroupExample();
        example.or().andGroupIdEqualTo(Gid);
        removeUserFromGroup(Uid, example);
    }

    @Override
    public Boolean hasGroup(Integer userId, String gName) {
        List<Group> groupList = selectByUserId(userId);
        for (Group g : groupList) {
            if (g.getGroupName().equals(gName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        //delete user-group map
        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andGroupIdEqualTo(groupId);
        userGroupMapper.deleteByExample(userGroupExample);

        //delete group-permission map        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or().andGroupIdEqualTo(groupId);
        List<GroupPermission> groupPermissionList = groupPermissionMapper.selectByExample(groupPermissionExample);
        groupPermissionMapper.deleteByExample(groupPermissionExample);

        List<Integer> pidList = new ArrayList<>();
        for (GroupPermission groupPermission : groupPermissionList) {
            pidList.add(groupPermission.getPermissionId());
        }

        //delete group's permission
        PermissionExample permissionExample = new PermissionExample();
        if (!pidList.isEmpty()) {
            permissionExample.or().andPermissionIdIn(pidList);
            permissionService.deleteByExample(permissionExample);
        }

        //delete group
        deleteByPrimaryKey(groupId);
    }

    private void removeUserFromGroup(Integer Uid, GroupExample groupExample) throws UserNotFoundException, GroupNotFountException {

        UserExample userExample = new UserExample();
        userExample.or().andUserIdEqualTo(Uid);
        if (userService.countByExample(userExample) == 0) {
            throw new UserNotFoundException("User id = " + Uid);
        }

        if (countByExample(groupExample) == 0) {
            throw new GroupNotFountException();
        }

        UserGroupExample ugExample = new UserGroupExample();
        ugExample.or().andGroupIdEqualTo(selectByExample(groupExample).get(0).getGroupId()).andUserIdEqualTo(Uid);
        userGroupMapper.deleteByExample(ugExample);
    }

    private void addUserToGroup(Integer Uid, GroupExample groupExample) throws UserNotFoundException, GroupNotFountException {
        UserExample userExample = new UserExample();
        userExample.or().andUserIdEqualTo(Uid);
        if (0 == userService.countByExample(userExample)) {
            throw new UserNotFoundException("user id = \"" + Uid + "\"");
        }
        List<Group> groupList = selectByExample(groupExample);
        if (groupList == null || groupList.size() == 0) {
            throw new GroupNotFountException();
        }
        Group group = groupList.get(0);
        UserGroup userGroup = new UserGroup(Uid, group.getGroupId());
        userGroupMapper.insert(userGroup);
    }

    private List<User> selectUserByGroupExample(GroupExample example) throws GroupNotFountException {
        List<Group> groupList = selectByExample(example);
        if (groupList == null || groupList.size() == 0) {
            throw new GroupNotFountException();
        }
        Group group = groupList.get(0);
        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andGroupIdEqualTo(group.getGroupId());
        List<UserGroup> userGroupList = userGroupMapper.selectByExample(userGroupExample);
        List<Integer> userIdList = new ArrayList<>();
        for (UserGroup ug :
                userGroupList) {
            userIdList.add(ug.getUserId());
        }
        if (userIdList.isEmpty()) {
            return new ArrayList<>();
        }
        UserExample userExample = new UserExample();
        userExample.or().andUserIdIn(userIdList);
        return userService.selectByExample(userExample);
    }
}
