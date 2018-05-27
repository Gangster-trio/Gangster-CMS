package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;

public interface UserService extends BaseService<User, UserExample> {
<<<<<<< HEAD
//    void deleteUser(Integer userId) throws UserNotFoundException, GroupNotFountException;
=======
    void deleteUser(Integer userId) ;
>>>>>>> b4033db... delete Group

    int createUser(User user);

    /**
     * 判断用户是否是超级管理员
     * @param userId
     * @return
     */

}
