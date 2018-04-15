package com.ganster.cms.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create by Yoke on 2018/1/30
 */
@SpringBootApplication(scanBasePackages = "com.ganster.cms")
@MapperScan("com.ganster.cms.core.dao.mapper")
public class CmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsAdminApplication.class, args);
    }
}
