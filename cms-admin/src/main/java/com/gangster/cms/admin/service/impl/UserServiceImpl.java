package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;
import com.gangster.cms.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    @Autowired
    private UserMapper userMapper;

    private static final String ADMIN = "admin";

    @Override
    public void deleteUser(Integer userId) {
        deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User user) {
        return createUser(user);
    }

    @Override
    public int createUser(User user) {
        user.setUserCreateTime(new Date());
        return super.insert(user);
    }
}