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

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

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

    @GetMapping("/find")
    @ResponseBody
    public AjaxData findUser(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        PageInfo pageInfo;
        int count = 0;
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIsNotNull();
        List<User> userList = userService.selectByExample(userExample);
        for (User i : userList) {
            count++;
        }
        if (page != null && limit != null) {
            pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> userService.selectByExample(userExample));
            return super.buildAjaxData(0, "success", count, (ArrayList) userList);
        } else {
            pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> userService.selectByExample(userExample));
            return super.buildAjaxData(0, "success", count, (ArrayList) userList);
        }
    }

    @GetMapping("/find/{UserId}")
    @ResponseBody
    public User fingUserById(@PathVariable("UserId") Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userService.selectByExample(userExample);
        User user = userList.get(0);
        return user;
    }

    @GetMapping("/findgroup/{UserId}")
    @ResponseBody
    public AjaxData findUserGroup(@PathVariable("UserId") Integer userId,@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        AjaxData ajaxData=new AjaxData();
        PageInfo pageInfo;
        int count=0;
        GroupExample groupExample=new GroupExample();
        List<Group> groupList = groupService.selectByUserId(userId);
        if (groupList != null && !groupList.isEmpty()) {
            for (Group i:groupList){
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

    @GetMapping("/findgroup")
    @ResponseBody
    public AjaxData findAllGroup() {
        int number=0;
        AjaxData ajaxData = new AjaxData();
        GroupExample groupExample = new GroupExample();
        groupService.selectByExample(groupExample);
        List<Group> groupList = groupService.selectByExample(groupExample);
        if (groupList != null && !groupList.isEmpty()) {
            ajaxData.setData((ArrayList) groupList);
            for (Group i:groupList){
                number++;
            }
            ajaxData.setCode(number);
            return ajaxData;
        }
        ajaxData.setMsg("查找失败");
        return ajaxData;
    }

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
