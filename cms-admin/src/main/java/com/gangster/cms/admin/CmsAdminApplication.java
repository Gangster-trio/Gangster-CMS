package com.gangster.cms.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create by Yoke on 2018/1/30
 */
@SpringBootApplication(scanBasePackages = "com.gangster.cms")
@MapperScan("com.gangster.cms.core.mapper")
public class CmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsAdminApplication.class, args);
    }
}
