package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service  与用户和组有关的所有操作
 */
@Service
public class UserConcreteService {
    private static final Logger logger = LoggerFactory.getLogger(UserConcreteService.class);

    @Autowired
    private UserService userService;


    /**
     * 添加一名新用户
     *
     * @param user 用户信息
     * @return 是否添加成功
     */
    public boolean addUser(User user) {
        user.setUserCreateTime(new Date());
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName());
        List<User> userList = userService.selectByExample(userExample);
        if (userList != null && !userList.isEmpty()) return false;
        else {
            userService.createUser(user);
            return true;
        }
    }

    /**
     * 更新用户信息
     *
     * @param userId 用户的Id
     * @param user   用户的具体信息
     * @return 是否更新成功
     */
    public boolean updateUser(Integer userId, User user) {
        if (userService.selectByPrimaryKey(userId) != null) {
            userService.updateByPrimaryKeySelective(user);
            return true;
        } else return false;
    }

    /**
     * 删除单个用户
     *
     * @param userId 用户的Id
     * @return 是否删除成功
     */
    public boolean deleteSingleUser(Integer userId) {
        if (userService.selectByPrimaryKey(userId) != null) {
                userService.deleteUser(userId);
                return true;
        }
        return false;
    }

    /**
     * 查找所有的用户
     *
     * @param page  查找信息的页数
     * @param limit 每页所显示的条数
     * @return PageInfo<User>
     */
    public PageInfo<User> listAllUser(Integer page, Integer limit) {
        UserExample userExample = new UserExample();
        PageInfo<User> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> userService.selectByExample(userExample));
        List<User> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return null;
        } else return pageInfo;
    }

    /**
     * 查找单个用户
     *
     * @param userId 用户的Id
     * @return 查找用户的信息
     */
    public User findSingleUser(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userService.selectByExample(userExample);
        return userList.get(0);
    }
}
