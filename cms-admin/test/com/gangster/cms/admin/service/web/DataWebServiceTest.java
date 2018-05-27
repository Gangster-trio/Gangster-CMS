package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.CmsAdminApplication;
import com.gangster.cms.common.pojo.CountEntry;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = CmsAdminApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DataWebServiceTest {
    @Autowired
    DataWebService dataWebService;


    @Test
    public void getCountTest() {
        PageInfo<CountEntry> info = dataWebService.getCount("site", null, 1527164347013L, null, 60 * 1000 * 10 * 10, 30, 0);
        info.getList().forEach(System.out::println);
    }
}