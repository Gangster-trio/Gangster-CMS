package com.ganster.cms.auth.controller;

import com.ganster.cms.core.service.GroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserGroupControllerTest {
    /**
     *
     */
    @Autowired
    private GroupService groupService;
    @Test
    public void deleteGroup() throws Exception {
        int groupId = 12;
        groupService.deleteGroup(groupId);
    }

}