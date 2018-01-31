package com.ganster.cms.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ganster.cms")
@MapperScan("com.ganster.cms.core.dao.mapper")
public class CmsWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsWebApplication.class, args);
    }
}
