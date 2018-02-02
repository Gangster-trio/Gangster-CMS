package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.PermissionNotFoundException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.User;

import java.util.List;

public interface GroupService extends BaseService<Group,GroupExample> {
    List<Group> selectByUserId(Integer id);

    List<User> selectUserByGroupId(Integer id) throws GroupNotFountException;

    List<User> selectUserByGroupName(String name) throws GroupNotFountException;

    Group findUserOwnGroup(Integer userId) throws UserNotFoundException, GroupNotFountException;

    void addUserToGroup(Integer Uid,Integer Gid) throws UserNotFoundException, GroupNotFountException;

    void addUserToGroup(Integer Uid,String gName) throws UserNotFoundException, GroupNotFountException;

    void removeUserFromGroup(Integer Uid,String gName) throws UserNotFoundException, GroupNotFountException;

    void removeUserFromGroup(Integer Uid,Integer Gid) throws UserNotFoundException, GroupNotFountException;

    void addPermissionToGroup(Integer Pid,Integer Gid) throws PermissionNotFoundException, GroupNotFountException;

    void addPermissionToGroup(String Pname,String Gname) throws PermissionNotFoundException, GroupNotFountException;

    void removePermissionFromGroup(Integer pid,Integer gid) throws PermissionNotFoundException, GroupNotFountException;

    void removePermissionFromGroup(String pName,String gName) throws PermissionNotFoundException, GroupNotFountException;

    Boolean hasGroup(Integer userId,String gName);
}
