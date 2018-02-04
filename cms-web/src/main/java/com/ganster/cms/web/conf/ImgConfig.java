package com.ganster.cms.web.conf;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ImgConfig extends WebMvcConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImgConfig.class);
    @Autowired
    SettingService settingService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + settingService.get(CmsConst.PIC_PATH_SETTING));
        LOGGER.info("Ganster CMS : mapped /img/** to {}",settingService.get(CmsConst.PIC_PATH_SETTING));
        super.addResourceHandlers(registry);
    }
}
