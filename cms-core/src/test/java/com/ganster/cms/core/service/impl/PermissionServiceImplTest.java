package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.CoreApplication;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.gangster.cms.common.pojo.Group;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.common.pojo.User;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class PermissionServiceImplTest {
    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    GroupService groupService;

    @Autowired
    SiteService siteService;

    @Test
    public void interTest() {
        final String userName = "@#$%^&";
        final String siteName = "$%^&&";
        User user = new User();
        user.setUserName(userName);
        userService.insert(user);

        Site site = new Site();
        site.setSiteName(siteName);
        siteService.insert(site);

        Group group = new Group();
        group.setGroupName(userName);
        groupService.insert(group);
        System.out.println(group.getGroupId());
        try {
            groupService.addUserToGroup(user.getUserId(), group.getGroupId());
        } catch (UserNotFoundException | GroupNotFountException e) {
            e.printStackTrace();
        }

        try {
            permissionService.addUserToSite(user.getUserId(), site.getSiteId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        List<Site> siteList = permissionService.findAllUserSite(user.getUserId());
        System.out.println(siteList);

        try {
            permissionService.addCategoryPermissionToUser(user.getUserId(), site.getSiteId(), 3, CmsConst.PERMISSION_READ);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        boolean hasP = permissionService.hasCategoryPermission(user.getUserId(), site.getSiteId(), 3, CmsConst.PERMISSION_READ);
        Assert.assertTrue(hasP);
    }

}