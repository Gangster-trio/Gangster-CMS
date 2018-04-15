package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.admin.dto.InformationObject;
import com.gangster.cms.admin.dto.Message;
import com.gangster.cms.common.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


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
        List<String> groupName = groupService.selectByUserId(userId).stream()
                .map(group -> "<input type='text' disabled='disabled' value='" + group.getGroupName() + "'" + "class='layui-input'" + "/>")
                .collect(Collectors.toList());
        informationObject.setUserGroup(groupName);
        message.setData(informationObject);
        return message;
    }

    @GetMapping("/getUserId")
    public Integer findUserId() {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        logger.info("++++++++++++++++" + userId);
        return userId;
    }

}
