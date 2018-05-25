package com.gangster.cms.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Create by Yoke on 2018/1/30
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.gangster.cms")
@MapperScan("com.gangster.cms.dao.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
public class CmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsAdminApplication.class, args);
    }
}
