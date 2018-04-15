package com.ganster.cms.admin;

import com.ganster.cms.admin.web.CmsCommonBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Create by Yoke on 2018/1/30
 */
@SpringBootApplication(scanBasePackages = "com.ganster.cms")
@MapperScan("com.ganster.cms.core.dao.mapper")
public class CmsAdminApplication {
    @Bean
    public CmsCommonBean commonBean() {
        return new CmsCommonBean();
    }

    public static void main(String[] args) {
        SpringApplication.run(CmsAdminApplication.class, args);
    }
}
