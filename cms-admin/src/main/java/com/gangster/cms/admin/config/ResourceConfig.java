package com.gangster.cms.admin.config;

import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.common.constant.CmsConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
