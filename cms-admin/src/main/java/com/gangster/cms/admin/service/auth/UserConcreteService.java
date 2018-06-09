package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.admin.util.StringUtil;
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
import java.util.stream.Stream;

/**
 * Service  与用户有关的所有操作
 */
@Service
public class UserConcreteService {
    private static final Logger logger = LoggerFactory.getLogger(UserConcreteService.class);

    @Autowired
    private UserService userService;


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

    public boolean updateUser(Integer userId, User user) {
        if (userService.selectByPrimaryKey(userId) != null) {
            userService.updateByPrimaryKeySelective(user);
            return true;
        } else return false;
    }

    public boolean deleteSingleUser(Integer userId) {
        if (userService.selectByPrimaryKey(userId) != null) {
            userService.deleteUser(userId);
            return true;
        }
        return false;
    }

    public PageInfo<User> listAllUser(Integer page, Integer limit) {
        UserExample userExample = new UserExample();
        PageInfo<User> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> userService.selectByExample(userExample));
        List<User> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return null;
        } else return pageInfo;
    }

    public User findSingleUser(Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userService.selectByExample(userExample);
        return userList.get(0);
    }

    public boolean batchDeleting(String userIdData) {
        return split(userIdData, userService);
    }

    private boolean split(String userIdData, UserService userService) {
        if (!StringUtil.isNullOrEmpty(userIdData)) {
            String[] userIds = userIdData.split(",");
            try {
                Stream.of(userIds).forEach(e -> userService.deleteUser(Integer.parseInt(e)));
            } catch (Exception e) {
                logger.error("删除用户时发生错误");
                e.printStackTrace();
                return false;
            }
            return true;
        } else return false;
    }
}