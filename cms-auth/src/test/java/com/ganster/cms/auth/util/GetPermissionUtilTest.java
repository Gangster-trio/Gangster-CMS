package com.ganster.cms.auth.util;

import com.ganster.cms.auth.authApplication;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.service.GroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = authApplication.class)
public class GetPermissionUtilTest {
    private static final Logger logger = LoggerFactory.getLogger(GetPermissionUtilTest.class);
    GetPermissionUtil getPermissionUtil = new GetPermissionUtil();
    @Autowired
    private GroupService groupService;

    @Test
    public void test() {
        GroupExample groupExample = new GroupExample();
        String string="find";
         List<String> strings= getPermissionUtil.getPermissionName(string);
        logger.info("groupList+++++++++++++++++++++"+strings.toString());
    }

}