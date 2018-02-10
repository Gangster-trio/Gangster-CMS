package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.CoreApplication;
import com.ganster.cms.core.service.SiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class SiteServiceImplTest {

    @Autowired
    SiteService siteService;

    @Test
    public void deleteSiteTest() {
        siteService.deleteSite(15);
    }
}