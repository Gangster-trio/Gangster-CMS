package com.ganster.cms.admin.service;


import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.pojo.UserExample;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.Page;

import java.util.Date;
import java.util.List;

/**
 * Service  与用户和组有关的所有操作
 */
@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    public Boolean index() {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> group = groupService.selectByUserId(userId);
        for (Group i : group) {
            if (i.getGroupName().equals("admin")) {
                return true;
            }
        }
        return false;
    }

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
            try {
                userService.deleteUser(userId);
                return true;
            } catch (UserNotFoundException e) {
                logger.error("++++++++++++++++++++++++++++用户为找到+++++++++++++++++++++++++");
            } catch (GroupNotFountException e) {
                logger.error("++++++++++++++++++++++++++++++++++用户组为空++++++++++++++++++++++++++++++++++");
            }
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

    /**
     * 通过用户Id，查找所属于的的用户组
     *
     * @param userId 用户的Id
     * @param page   查找信息的页数
     * @param limit  每页所显示的条数
     * @return PageInfo<Group>
     */
    public PageInfo<Group> findUserGroup(Integer userId, Integer page, Integer limit) {
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> groupService.selectByUserId(userId));
    }

    /**
     * 通过用户Id和用户组Id，将用户从用户组中移出
     *
     * @param userId  用户的Id
     * @param groupId 组Id
     * @return 是否删除成功
     */
    public boolean deleteUserGroup(Integer userId, Integer groupId) {
        try {
            groupService.removeUserFromGroup(userId, groupId);
            return true;
        } catch (UserNotFoundException | GroupNotFountException e) {
            logger.error("用户或组不存在");
        }
        return false;
    }

    /**
     * 查找所有的用户组
     *
     * @return 查找到的所有用户组
     */
    public List<Group> findAllGroup(Integer page,Integer limit) {
        GroupExample groupExample = new GroupExample();
        List<Group> groupList = groupService.selectByExample(groupExample);
        if (groupList != null && !groupList.isEmpty()) {
            return groupList;
        } else return null;
    }

    /**
     * 通过用户Id和用户组Id，来向用户组中添加用户
     * @param groupId    用户组Id
     * @param userId     用户Id
     * @return  是否添加成功
     */
    public boolean addGroupToUser(Integer groupId, Integer userId) {
        List<Group> groupList = groupService.selectByUserId(userId);
        for (Group i : groupList) {
            if (groupId.equals(i.getGroupId())) {
                return false;
            }
        }
        try {
            groupService.addUserToGroup(userId, groupId);
            return true;
        } catch (UserNotFoundException | GroupNotFountException e) {
            e.printStackTrace();
        }
        return false;
    }
}
