package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.admin.exception.GroupNotFountException;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.common.pojo.Group;
import com.gangster.cms.common.pojo.GroupExample;
import com.gangster.cms.common.pojo.User;

import java.util.List;

public interface GroupService extends BaseService<Group,GroupExample> {
    List<Group> selectByUserId(Integer id);

    List<User> selectUserByGroupId(Integer id) throws GroupNotFountException;

    List<User> selectUserByGroupName(String name) throws GroupNotFountException;

    Group findUserOwnGroup(Integer userId) throws UserNotFoundException;

    void addUserToGroup(Integer Uid,Integer Gid) throws UserNotFoundException, GroupNotFountException;

    void addUserToGroup(Integer Uid,String gName) throws UserNotFoundException, GroupNotFountException;

    void removeUserFromGroup(Integer Uid,String gName) throws UserNotFoundException, GroupNotFountException;

    void removeUserFromGroup(Integer Uid,Integer Gid) throws UserNotFoundException, GroupNotFountException;

    Boolean hasGroup(Integer userId,String gName);

    void deleteGroup(Integer groupId);
}
