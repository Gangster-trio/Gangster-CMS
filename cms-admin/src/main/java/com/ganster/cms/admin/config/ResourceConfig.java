package com.ganster.cms.admin.config;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Autowired
    SettingService settingService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        ///"
        registry.addResourceHandler("/pic/**").addResourceLocations("file:" + settingService.get(CmsConst.PIC_PATH_SETTING)).setCachePeriod(1);
    }
}
