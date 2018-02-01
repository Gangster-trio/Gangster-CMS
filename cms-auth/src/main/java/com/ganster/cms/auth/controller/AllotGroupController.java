package com.ganster.cms.auth.controller;

import com.ganster.cms.auth.Exception.InformationException;
import com.ganster.cms.auth.util.GetPermissionUtil;
import com.ganster.cms.auth.util.PInformationUtil;
import com.ganster.cms.auth.util.RInformationUtil;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.PermissionNotFoundException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class AllotGroupController {
    private static final Logger logger = LoggerFactory.getLogger(AllotGroupController.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @RequiresPermissions("group")
    @RequestMapping("/add")
    public void addGroup(@RequestParam(value = "UserId") Integer userId, @RequestParam(value = "GroupName") String groupName) {
        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andGroupNameEqualTo(groupName);
        List<Group> groupList = groupService.selectByExample(groupExample);
        if (userId != null) {
            try {
                if (groupList == null) {
                    RInformationUtil rInformationUtil=new RInformationUtil();
                    rInformationUtil.dealInfromation(groupName);
                    Group group = new Group();
                    group.setGroupName(groupName);
                    groupService.insert(group);
                    groupService.addUserToGroup(userId, groupName);
                    groupService.addPermissionToGroup(groupName,"update:"+group.getGroupId());
                    groupService.addPermissionToGroup(groupName,"delect:"+group.getGroupId());
                    groupService.addPermissionToGroup(groupName,"find:"+group.getGroupId());
                } else {
                    for (Group i : groupList) {
                        groupService.addUserToGroup(userId, i.getGroupName());
                    }
                }
            } catch (UserNotFoundException e) {
                logger.info("用户未找到");
            } catch (GroupNotFountException e) {
                logger.info("用户组未找到");
            } catch (InformationException e) {
                logger.info("用户组信息不正确");
            } catch (PermissionNotFoundException e) {
                logger.info("用户组未找到");
            }
        }
    }

    @RequiresPermissions("group")
    @RequestMapping("/delect")
    public int delectGroup(@RequestParam(value = "GroupId") Integer groupId) {
        Subject subject = SecurityUtils.getSubject();
        GetPermissionUtil getPermissionUtil = new GetPermissionUtil();
        List<String> permissionName = getPermissionUtil.getPermissionName("delect");
        for (String i : permissionName) {
            if (subject.isPermitted(i)) {
                PInformationUtil pInformationUtil = new PInformationUtil();
                try {
                    pInformationUtil.dealInfromation(i);
                    Group group = groupService.selectByPrimaryKey(Integer.parseInt(pInformationUtil.getId()));
                    if (group.getGroupId().equals(groupId)){
                        return groupService.deleteByPrimaryKey(groupId);
                    } else continue;
                } catch (InformationException e) {
                    logger.info("权限信息异常");
                }
            }
        }
        return 0;
    }

    @RequiresRoles("group")
    @RequestMapping("/update")
    public int updateGroup(@RequestParam(value = "Group") Group group) {
        GetPermissionUtil getPermissionUtil = new GetPermissionUtil();
        List<String> permissionName = getPermissionUtil.getPermissionName("update");
        Subject subject = SecurityUtils.getSubject();
        for (String i : permissionName) {
            if (subject.isPermitted(i)) {
                PInformationUtil pInformationUtil = new PInformationUtil();
                try {
                    pInformationUtil.dealInfromation(i);
                    Group needUpdateGroup = groupService.selectByPrimaryKey(Integer.parseInt(pInformationUtil.getId()));
                    if (needUpdateGroup.getGroupId().equals(group.getGroupId())) {
                        return groupService.updateByPrimaryKey(group);
                    } else continue;
//                    if (group == null) break;
                } catch (InformationException e) {
                    logger.info("权限信息异常");
                }
            }
        }
        return 0;
    }

    @RequiresRoles("group")
    @RequestMapping("/find")
    public List<Group> findGroups() {
        List<Group> groupList = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        GetPermissionUtil getPermissionUtil = new GetPermissionUtil();
        List<String> permissionName = getPermissionUtil.getPermissionName("group");
        for (String i : permissionName) {
            if (subject.isPermitted(i)) {
                PInformationUtil pInformationUtil = new PInformationUtil();
                try {
                    pInformationUtil.dealInfromation(i);
                    Group group = groupService.selectByPrimaryKey(Integer.parseInt(pInformationUtil.getId()));
                    if (group == null) break;
                    groupList.add(group);
                } catch (InformationException e) {
                    logger.info("权限信息异常");
                }
            }
        }
        return groupList;
    }
}
