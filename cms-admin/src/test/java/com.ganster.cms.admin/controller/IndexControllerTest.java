package com.ganster.cms.admin.controller;

import com.gangster.cms.admin.CmsAdminApplication;
import com.gangster.cms.admin.exception.GroupNotFountException;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.common.pojo.Group;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.common.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Create by Yoke on 2018/2/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsAdminApplication.class)
public class IndexControllerTest {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private PermissionService permissionService;

    @Test
    public void test() {
        User user = new User();
        user.setUserName("test");
        user.setUserPassword("123");
        userService.insert(user);
        Group group = new Group();
        group.setGroupName("test");
        groupService.insert(group);
        try {
            groupService.addUserToGroup(user.getUserId(), group.getGroupId());
            permissionService.addUserToSite(user.getUserId(), 1);

            List<Site> list = permissionService.findAllUserSite(user.getUserId());

            System.out.println(list.size());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (GroupNotFountException e) {
            e.printStackTrace();
        }
    }
}

