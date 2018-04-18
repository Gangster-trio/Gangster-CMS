package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.admin.service.AuthService;
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
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;


    /**
     * 添加用户
     *
     * @param user 用户对象(用户信息)
     * @return Message 添加用户是否成功
     */
    @SystemControllerLog(description = "添加用户对象")
    @PostMapping("/add")
    @ResponseBody
    public MessageDto addUser(@RequestBody User user) {
        if (authService.addUser(user)) {
            return  MessageDto.success(null);
        }
       return MessageDto.fail(1,"添加用户失败");
    }

    /**
     * 修改用户信息
     *
     * @param userid 用户Id
     * @param user   原始用户信息
     * @return Message  修改用户是否成功
     */
    @SystemControllerLog(description = "修改用户对象")
    @PostMapping(value = "/update/{userid}")
    @ResponseBody
    public MessageDto updateUser(@PathVariable("userid") Integer userid, @RequestBody User user) {
        if (authService.updateUser(userid, user)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1,"修改用户失败");
    }

    /**
     * 删除用户
     *
     * @param userId 用户的Id
     * @return int   删除用户数量
     */
    @SystemControllerLog(description = "删除用户对象")
    @GetMapping("/delete/{UserId}")
    @ResponseBody
    public MessageDto deleteUser(@PathVariable("UserId") Integer userId) {
        if (authService.deleteSingleUser(userId)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1,"删除对象失败");
    }

    /**
     * 查找所有的用户
     *
     * @param page  查找信息的页数
     * @param limit 每页所显示的条数
     * @return AjaxData 查找到的所有用户
     */
    @GetMapping("/find")
    @ResponseBody
    public AjaxData findUser(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<User> pageInfo = authService.listAllUser(page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 通过用户Id查找用户
     *
     * @param userId 用户的Id
     * @return User 查找到的用户
     */
    @GetMapping("/find/{UserId}")
    @ResponseBody
    public User fingUserById(@PathVariable("UserId") Integer userId) {
      return authService.findSingleUser(userId);
    }

    /**
     * 通过用户Id，查找所属于的的用户组
     *
     * @param userId 用户的Id
     * @param page   查找信息的页数
     * @param limit  每页信息的条数
     * @return AjaxData 通过用户Id，查找所属于的用户组
     */
    @GetMapping("/findgroup/{UserId}")
    @ResponseBody
    public AjaxData findUserGroup(@PathVariable("UserId") Integer userId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
       PageInfo<Group> pageInfo= authService.findUserGroup(userId,page,limit);
       if (pageInfo==null){
           return new AjaxData(1,"failed",0,null);
       }
       return new AjaxData(0,"success",pageInfo.getTotal(),pageInfo.getList());
    }

    /**
     * 通过用户Id和用户组Id，将用户从用户组中移出
     *
     * @param userId  用户的Id
     * @param groupId 用户组Id
     * @return int 移出用户的数量
     */
    @ResponseBody
    @GetMapping("/deletegroup/{UserId}/{GroupId}")
    public int deleteUserGroup(@PathVariable("UserId") Integer userId, @PathVariable("GroupId") Integer groupId) {
       if (authService.deleteUserGroup(userId,groupId)){
           return 1;
       }
       return 0;
    }

    /**
     * 查找所有的用户组
     *
     * @return AjaxData  查找到的信息
     */
    @GetMapping("/findgroup")
    @ResponseBody
    public AjaxData findAllGroup(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
     List<Group> groups= authService.findAllGroup(page,limit);
     if (groups==null){
         return new AjaxData(1,"failed",0,null);
     }
        return new AjaxData(0,"success",groups);
    }

    /**
     * 通过用户Id和用户组Id，来向用户组中添加用户
     *
     * @param groupId 用户组Id
     * @param userId  用户Id
     * @return Integer 为用户添加的角色组数量
     */
    @GetMapping("/addGroupToUse/{GroupId}/{UserId}")
    @ResponseBody
    public Integer addGroupToUser(@PathVariable("GroupId") Integer groupId, @PathVariable("UserId") Integer userId) {
        if (!this.index()) {
            return 0;
        }
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
