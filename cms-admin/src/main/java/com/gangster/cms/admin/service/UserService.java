package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;

public interface UserService extends BaseService<User, UserExample> {
    void deleteUser(Integer userId) ;

    int createUser(User user);

    /**
     * 判断用户是否是超级管理员
     * @param userId
     * @return
     */

}
