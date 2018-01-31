package com.ganster.cms.auth.controller;

import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.service.GroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    @RequiresPermissions("add")
    @RequestMapping("/add")
    public void addGroup(@RequestParam(value = "UserId", required = false) Integer userId, @RequestParam(value = "GroupName") String groupName) {
        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andGroupNameEqualTo(groupName);
        List<Group> groupList = groupService.selectByExample(groupExample);
        if (userId != null) {
            try {
                if (groupList == null) {
                    Group group = new Group();
                    group.setGroupName(groupName);
                    groupService.insert(group);
                    groupService.addUserToGroup(userId, groupName);
                } else {
                    for (Group i : groupList) {
                        groupService.addUserToGroup(userId, i.getGroupName());
                    }
                }
            } catch (UserNotFoundException e) {
                logger.info("用户未找到");
            } catch (GroupNotFountException e) {
                logger.info("用户组未找到");
            }
        } else {
            Group group = new Group();
            group.setGroupName(groupName);
            groupService.insert(group);
        }
    }

    @RequiresPermissions("delect")
    @RequestMapping("/delect")
    public int delectGroup(@RequestParam(value = "GroupId") Integer groupId) {
        return groupService.deleteByPrimaryKey(groupId);
    }

    @RequiresPermissions("update")
    @RequestMapping("/update")
    public int updateGroup(@RequestParam(value = "Group") Group group) {
        return groupService.updateByPrimaryKey(group);
    }

    @RequiresPermissions("find")
    @RequestMapping("/find")
    public List<Group> findGroups() {
        List<Group> groupList = new ArrayList<>();
        for (int i = 1; ; i++) {
            Group group = groupService.selectByPrimaryKey(i);
            if (group != null) {
                groupList.add(group);
            } else break;
        }
        return groupList;
    }
}
