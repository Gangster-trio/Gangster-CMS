package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;

public interface UserService extends BaseService<User, UserExample> {
    void deleteUser(Integer userId) throws UserNotFoundException, GroupNotFountException;

    int createUser(User user);

    /**
     * 判断用户是否是超级管理员
     * @param userId
     * @return
     */

}
