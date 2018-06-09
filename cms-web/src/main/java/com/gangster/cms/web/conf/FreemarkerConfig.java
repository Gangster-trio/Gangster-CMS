package com.gangster.cms.web.conf;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.SettingEntry;
import com.gangster.cms.dao.mapper.SettingEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreemarkerConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FreemarkerConfig.class);

    private final
    SettingEntryMapper settingEntryMapper;

    @Autowired
    public FreemarkerConfig(SettingEntryMapper settingEntryMapper) {
        this.settingEntryMapper = settingEntryMapper;
    }

    @Bean
    public FreeMarkerConfigurer loadPathConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        final String DEFAULT_SKIN_PATH = "classpath:templates";
        String dbSkinPath = "";
        SettingEntry skinPathEntry = settingEntryMapper.selectByPrimaryKey(CmsConst.SKIN_PATH_SETTING);
        if (skinPathEntry!=null){
            dbSkinPath = skinPathEntry.getSysValue();
        }
        if (!dbSkinPath.isEmpty()) {
            configurer.setTemplateLoaderPaths(DEFAULT_SKIN_PATH, dbSkinPath);
        } else {
            configurer.setTemplateLoaderPath(DEFAULT_SKIN_PATH);
        }
        LOGGER.info("Gangster CMS : templates path = {},{}", DEFAULT_SKIN_PATH, dbSkinPath);

        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setRequestContextAttribute("request");
        return resolver;
    }

}