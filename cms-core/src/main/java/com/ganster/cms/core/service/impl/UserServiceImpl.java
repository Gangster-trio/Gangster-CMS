package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.UserGroupMapper;
import com.ganster.cms.core.dao.mapper.UserMapper;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.pojo.UserExample;
import com.ganster.cms.core.pojo.UserGroupExample;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {
    @Autowired
    GroupService groupService;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Override
    public void deleteUser(Integer userId) throws UserNotFoundException, GroupNotFountException {
        //delete user's own group
        Group group = groupService.findUserOwnGroup(userId);
        groupService.deleteGroup(group.getGroupId());

        UserGroupExample userGroupExample = new UserGroupExample();
        userGroupExample.or().andUserIdEqualTo(userId);
        userGroupMapper.deleteByExample(userGroupExample);
    }
}
