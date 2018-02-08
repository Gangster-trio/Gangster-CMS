package com.ganster.cms.auth.controller;


import com.ganster.cms.auth.dto.AjaxData;
import com.ganster.cms.auth.dto.Message;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller   与用户有关的所有操作
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    /**
     *
     * 添加用户
     * @param user
     * @return   Message 添加用户是否成功
     */

    @PostMapping("/add")
    @ResponseBody
    public Message addUser(@RequestBody User user) {
        user.setUserCreateTime(new Date());
        Message message = new Message();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(user.getUserName());
        List<User> userList = userService.selectByExample(userExample);
        if (userList != null && !userList.isEmpty()) {
            message.setMsg("用户已存在");
            message.setCode(1);
        } else {
            userService.createUser(user);
            message.setCode(0);
            message.setMsg("成功添加");
        }

        return message;
    }

    /**
     * 修改用户信息
     *
     * @param userid
     * @param user
     * @return  Message  修改用户是否成功
     */
    @PostMapping(value = "/update/{userid}")
    @ResponseBody
    public Message updateUser(@PathVariable("userid") Integer userid, @RequestBody User user) {
        Message message = new Message();
        int updateNumber;
        if (userService.selectByPrimaryKey(userid) != null) {
            updateNumber = userService.updateByPrimaryKeySelective(user);
            message.setData(updateNumber);
            message.setCode(0);
            message.setMsg("success");
        } else {
            message.setMsg("用户不存在");
        }
        return message;
    }

    /**
     *  删除用户
     * @param userId
     * @return   int   删除用户数量
     */
    @GetMapping("/delete/{UserId}")
    @ResponseBody
    public int deleteUser(@PathVariable("UserId") Integer userId) {
        int message = 0;
        int delectNumber;
        if (userService.selectByPrimaryKey(userId) != null) {
            try {
                userService.deleteUser(userId);
                message = 1;
            } catch (UserNotFoundException e) {
                logger.info("++++++++++++++++++++++++++++用户为找到+++++++++++++++++++++++++");
            } catch (GroupNotFountException e) {
                logger.info("++++++++++++++++++++++++++++++++++用户组为空++++++++++++++++++++++++++++++++++");
            }
        }
        return message;
    }

    /**
     *  查找所有的用户
     * @param page
     * @param limit
     * @return  AjaxData 查找到的所有用户
     */
    @GetMapping("/find")
    @ResponseBody
    public AjaxData findUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 10;
        }
        UserExample userExample = new UserExample();
        PageInfo<User> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> userService.selectByExample(userExample));
        List<User> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return super.buildAjaxData(1, "false", 0, null);
        } else {
            return super.buildAjaxData(0, "true", pageInfo.getTotal(), list);
        }
    }

    /**
     *  通过用户Id查找用户
     * @param userId
     * @return  User 查找到的用户
     */
    @GetMapping("/find/{UserId}")
    @ResponseBody
    public User fingUserById(@PathVariable("UserId") Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userService.selectByExample(userExample);
        User user = userList.get(0);
        return user;
    }

    /**
     * 通过用户Id，查找所属于的的用户组
     *
     * @param userId
     * @param page
     * @param limit
     * @return  AjaxData 通过用户Id，查找所属于的用户组
     */
    @GetMapping("/findgroup/{UserId}")
    @ResponseBody
    public AjaxData findUserGroup(@PathVariable("UserId") Integer userId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        AjaxData ajaxData = new AjaxData();
        PageInfo pageInfo;
        int count = 0;
        GroupExample groupExample = new GroupExample();
        List<Group> groupList = groupService.selectByUserId(userId);
        if (groupList != null && !groupList.isEmpty()) {
            for (Group i : groupList) {
                count++;
            }
            if (page != null && limit != null) {
                pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> groupService.selectByExample(groupExample));
                return super.buildAjaxData(0, "success", count, (ArrayList) groupList);
            } else {
                pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> groupService.selectByExample(groupExample));
                return super.buildAjaxData(0, "success", count, (ArrayList) groupList);
            }
        }
        ajaxData.setMsg("查找失败");
        return ajaxData;
    }

    /**
     * 通过用户Id和用户组Id，将用户从用户组中移出
     *
     * @param userId
     * @param groupId
     * @return int 移出用户的数量
     */
    @ResponseBody
    @GetMapping("/deletegroup/{UserId}/{GroupId}")
    public int deleteUserGroup(@PathVariable("UserId") Integer userId, @PathVariable("GroupId") Integer groupId) {
        Integer message;
        try {
            groupService.removeUserFromGroup(userId, groupId);
            message = 1;
        } catch (Exception e) {
            message = 0;
        }
        return message;
    }

    /**
     * 查找所有的用户组
     * @return AjaxData  查找到的信息
     */
    @GetMapping("/findgroup")
    @ResponseBody
    public AjaxData findAllGroup() {
        int number = 0;
        AjaxData ajaxData = new AjaxData();
        GroupExample groupExample = new GroupExample();
        groupService.selectByExample(groupExample);
        List<Group> groupList = groupService.selectByExample(groupExample);
        if (groupList != null && !groupList.isEmpty()) {
            ajaxData.setData((ArrayList) groupList);
            for (Group i : groupList) {
                number++;
            }
            ajaxData.setCode(number);
            return ajaxData;
        }
        ajaxData.setMsg("查找失败");
        return ajaxData;
    }

    /**
     * 通过用户Id和用户组Id，来向用户组中添加用户
     * @param groupId
     * @param userId
     * @return Integer 为用户添加的角色组数量
     */
    @GetMapping("/addGroupToUse/{GroupId}/{UserId}")
    @ResponseBody
    public Integer addGroupToUser(@PathVariable("GroupId") Integer groupId, @PathVariable("UserId") Integer userId) {
        int message;
        List<Group> groupList = groupService.selectByUserId(userId);
        for (Group i : groupList) {
            if (groupId.equals(i.getGroupId())) {
                message = 1;
                return message;
            }
        }
        try {
            groupService.addUserToGroup(userId, groupId);
            message = 1;
            return message;
        } catch (Exception e) {
            logger.info("添加失败");
            message = 0;
        }
        return message;
    }
}
