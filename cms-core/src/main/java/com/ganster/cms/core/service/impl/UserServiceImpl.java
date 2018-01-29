package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.UserMapper;
import com.ganster.cms.core.dao.pojo.User;
import com.ganster.cms.core.dao.pojo.UserExample;
import com.ganster.cms.core.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {
}
