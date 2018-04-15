package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
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
