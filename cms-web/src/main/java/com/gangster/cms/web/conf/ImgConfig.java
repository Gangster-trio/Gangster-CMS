package com.gangster.cms.web.conf;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.dao.mapper.SettingEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImgConfig implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImgConfig.class);
    private final
    SettingEntryMapper settingEntryMapper;

    @Autowired
    public ImgConfig(SettingEntryMapper settingEntryMapper) {
        this.settingEntryMapper = settingEntryMapper;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //pic path must end with "/"
        registry.addResourceHandler("/pic/**").addResourceLocations("file:" + settingEntryMapper.selectByPrimaryKey(CmsConst.PIC_PATH_SETTING).getSysValue());
        LOGGER.info("Gangster CMS : mapped /pic/** to {}", settingEntryMapper.selectByPrimaryKey(CmsConst.PIC_PATH_SETTING).getSysValue());
    }
}
