package com.ganster.cms.auth.controller;

import com.ganster.cms.auth.Exception.InformationException;
import com.ganster.cms.auth.util.PInformationUtil;
import com.ganster.cms.auth.util.RInformationUtil;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.PermissionNotFoundException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.apache.shiro.SecurityUtils;
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

    public List<String> getPermissionName(String groupName, String permission) {
        List<String> permissionName = new ArrayList<>();
        PInformationUtil pInformationUtil = new PInformationUtil();
        Subject subject = SecurityUtils.getSubject();
        Integer id = (Integer) subject.getSession().getAttribute("id");
        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andGroupNameEqualTo(groupName+":"+id);
        List<Group> groupList = groupService.selectByExample(groupExample);
        for (Group group : groupList) {
            try {
                List<Permission> permissionList = permissionService.selectByGroupId(group.getGroupId());
                for (Permission i : permissionList) {
                    int j = 0;
                    try {
                        pInformationUtil.dealInfromation(i.getPermissionName());
                        String name = permission + ":" + pInformationUtil.getId();
                        permissionName.add(name);
                        j++;
                    } catch (Exception e) {
                        break;
                    }
                }
            } catch (GroupNotFountException e) {
                e.printStackTrace();
            }
        }
        return permissionName;
    }

    public String index() {
        RInformationUtil rInformationUtil = new RInformationUtil();
        Subject subject = SecurityUtils.getSubject();
        Integer id = (Integer) subject.getSession().getAttribute("id");
        List<Group> group = groupService.selectByUserId(id);
        List<String> groupName = new ArrayList<>();
        for (Group i : group) {
            try {
                rInformationUtil.dealInfromation(i.getGroupName());
                groupName.add(rInformationUtil.getRolename());
            } catch (Exception e) {
                logger.info("++++++++++++++++++++ index        错误++++++++++++++++++++");
                return "/403.html";
            }
        }
        for (String i : groupName) {
            if (groupName.equals("group"))
                return "ok";
        }
         return "/login/403.html";
    }


    @RequestMapping("/add")
    public void addGroup(@RequestParam(value = "UserId") Integer userId, @RequestParam(value = "GroupName") String groupName) {
        this.index();
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
//                    groupService.addCategoryPermissionToGroup(groupName,"update:"+group.getGroupId());
//                    groupService.addCategoryPermissionToGroup(groupName, "delete:" + group.getGroupId());
//                    groupService.addCategoryPermissionToGroup(groupName,"find:"+group.getGroupId());
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
            }catch (Exception e){
                logger.info("信息错误");
            }
        }
    }


    @RequestMapping("/delete")
    public int deleteGroup(@RequestParam(value = "GroupId") Integer groupId) {
        this.index();
        Subject subject = SecurityUtils.getSubject();
        List<String> permissionName = this.getPermissionName("group", "delectgroup");
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


    @RequestMapping("/update")
    public int updateGroup(@RequestParam(value = "Group") Group group) {
        this.index();
        List<String> permissionName = this.getPermissionName("group", "updategroup");
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
                } catch (InformationException e) {
                    logger.info("权限信息异常");
                }
            }
        }
        return 0;
    }


    @RequestMapping("/find")
    public List<Group> findGroups() {
        this.index();
        List<Group> groupList = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        List<String> permissionName = this.getPermissionName("group", "findgroup");
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
