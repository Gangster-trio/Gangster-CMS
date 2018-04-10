package com.ganster.cms.core.util;

import com.ganster.cms.core.CoreApplication;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class IPUtilTest {
    @Test
    public void test() throws IOException, GeoIp2Exception {
        IPUtil.getAddr("111.13.101.208");
        IPUtil.getAddr("101.201.172.229");
        IPUtil.getAddr("172.217.160.78");
    }
}
