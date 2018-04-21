package com.gangster.cms.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Create by Yoke on 2018/1/30
 */
@SpringBootApplication(scanBasePackages = "com.gangster.cms")
@MapperScan("com.gangster.cms.dao.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class CmsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsAdminApplication.class, args);
    }
}
