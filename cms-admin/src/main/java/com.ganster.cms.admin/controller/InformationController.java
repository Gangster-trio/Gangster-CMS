package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.InformationObject;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


/**
 * Controller 查找用户的个人信息
 */
@RestController
@RequestMapping("/information")
public class InformationController {
    public static final Logger logger = LoggerFactory.getLogger(InformationController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/{UserId}")
    public Message findInformation(@PathVariable("UserId") Integer userId) {
        Message message = new Message();
        InformationObject informationObject = new InformationObject();
        User user = userService.selectByPrimaryKey(userId);
        informationObject.setUserName(user.getUserName());
        informationObject.setUserPhone(user.getUserPhone());
        informationObject.setUserEmail(user.getUserEmail());
        informationObject.setUserStatus(user.getUserStatus());
        informationObject.setUserCreateTime(user.getUserCreateTime());
        informationObject.setUserOrg(user.getUserOrg());
        List<Group> groupList = groupService.selectByUserId(userId);
        List<String> groupName = new ArrayList<>();
        for (Group i : groupList) {
            String string = "<input type='text' disabled='disabled' value='" + i.getGroupName() + "'"+"class='layui-input'" + "/>";
            groupName.add(string);
        }
        informationObject.setUserGroup(groupName);
        message.setData(informationObject);
        return message;
    }

    @GetMapping("/getUserId")
    public Integer findUserId(){
        Integer userId= (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        logger.info("++++++++++++++++"+userId);
        return userId;
    }

}
