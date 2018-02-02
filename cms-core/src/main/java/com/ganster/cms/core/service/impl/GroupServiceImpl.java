package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.GroupMapper;
import com.ganster.cms.core.dao.mapper.GroupPermissionMapper;
import com.ganster.cms.core.dao.mapper.UserGroupMapper;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper,Group,GroupExample> implements GroupService {
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
        if (userGroupList == null) {
            return new ArrayList<>();
        }
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
    public Group findUserOwnGroup(Integer userId) throws UserNotFoundException, GroupNotFountException {
        User user = userService.selectByPrimaryKey(userId);
        if (user == null) {
            throw new UserNotFoundException(userId.toString());
        }

        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andUserIdEqualTo(userId);
        List<UserGroup> userGroupList = userGroupMapper.selectByExample(userGroupExample);
        if (userGroupList == null || userGroupList.isEmpty()) {
            throw new GroupNotFountException();
        }
        //user's group
        for (UserGroup userGroup : userGroupList) {
            Group group = selectByPrimaryKey(userGroup.getGroupId());
            if (group == null) {
                throw new GroupNotFountException(userGroup.getGroupId().toString());
            }
            if (group.getGroupName().equals(user.getUserName())) {
                return group;
            }
        }
        //user's group not found
        throw new GroupNotFountException(user.getUserName());
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
        for (Group g:groupList){
            if (g.getGroupName().equals(gName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteGroup(Integer groupId) {
        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andGroupIdEqualTo(groupId);
        userGroupMapper.deleteByExample(userGroupExample);

        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.or().andGroupIdEqualTo(groupId);
        List<GroupPermission> groupPermissionList = groupPermissionMapper.selectByExample(groupPermissionExample);
        if (groupPermissionList != null) {
            for (GroupPermission groupPermission : groupPermissionList) {
                permissionService.deleteByPrimaryKey(groupPermission.getPermissionId());
            }
        }
        groupPermissionMapper.deleteByExample(groupPermissionExample);
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
        UserExample userExample = new UserExample();
        userExample.or().andUserIdIn(userIdList);
        return userService.selectByExample(userExample);
    }
}
