package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.service.auth.GroupConcreteService;
import com.gangster.cms.admin.service.auth.UserConcreteService;
import com.gangster.cms.common.pojo.Group;
import com.gangster.cms.common.pojo.User;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller   与用户有关的所有操作
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserConcreteService userConcreteService;
    @Autowired
    private GroupConcreteService groupConcreteService;
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
        if (userConcreteService.addUser(user)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "添加用户失败");
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
        if (userConcreteService.updateUser(userid, user)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "修改用户失败");
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
        if (userConcreteService.deleteSingleUser(userId)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "删除对象失败");
    }

    /**
     * 查找所有的用户
     *
     * @param page  查找信息的页数
     * @param limit 每页所显示的条数
     * @return AjaxData 查找到的所有用户
     */
    @SystemControllerLog(description = "查找用户")
    @GetMapping("/find")
    @ResponseBody
    public AjaxData findUser(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<User> pageInfo = userConcreteService.listAllUser(page, limit);
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
    @SystemControllerLog(description = "查找单个用户")
    @GetMapping("/find/{UserId}")
    @ResponseBody
    public User fingUserById(@PathVariable("UserId") Integer userId) {
        return userConcreteService.findSingleUser(userId);
    }

    /**
     * 通过用户Id，查找所属于的的用户组
     *
     * @param userId 用户的Id
     * @param page   查找信息的页数
     * @param limit  每页信息的条数
     * @return AjaxData 通过用户Id，查找所属于的用户组
     */
    @SystemControllerLog(description = "通过用户Id，查找所属于的的用户组")
    @GetMapping("/findgroup/{UserId}")
    @ResponseBody
    public AjaxData findUserGroup(@PathVariable("UserId") Integer userId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "15") Integer limit) {
        PageInfo<Group> pageInfo = groupConcreteService.findUserGroup(userId, page, limit);
        if (pageInfo == null) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 通过用户Id和用户组Id，将用户从用户组中移出
     *
     * @param userId  用户的Id
     * @param groupId 用户组Id
     * @return int 移出用户的数量
     */
    @SystemControllerLog(description = "通过用户Id和用户组Id，将用户从用户组中移出")
    @ResponseBody
    @GetMapping("/deletegroup/{UserId}/{GroupId}")
    public int deleteUserGroup(@PathVariable("UserId") Integer userId, @PathVariable("GroupId") Integer groupId) {
        if (groupConcreteService.deleteUserGroup(userId, groupId)) {
            return 1;
        }
        return 0;
    }

    /**
     * 查找所有的用户组
     *
     * @return AjaxData  查找到的信息
     */
    @SystemControllerLog(description = "查找所有的用户组")
    @GetMapping("/findgroup")
    @ResponseBody
    public AjaxData findAllGroup() {
        List<Group> groups = groupConcreteService.findAllGroup();
        if (groups == null) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", groups.size(), groups);
    }

    /**
     * 通过用户Id和用户组Id，来向用户组中添加用户
     *
     * @param groupId 用户组Id
     * @param userId  用户Id
     * @return Integer 为用户添加的角色组数量
     */
    @SystemControllerLog(description = "通过用户Id和用户组Id，来向用户组中添加用户")
    @GetMapping("/addGroupToUse/{GroupId}/{UserId}")
    @ResponseBody
    public Integer addGroupToUser(@PathVariable("GroupId") Integer groupId, @PathVariable("UserId") Integer userId) {
        List<Group> groupList = groupService.selectByUserId(userId);
        for (Group i : groupList) {
            if (groupId.equals(i.getGroupId())) {
                return 1;
            }
        }
        if (groupConcreteService.addUserToGroup(groupId, userId)) {
            return 1;
        } else return 0;
    }
}
