package com.ganster.cms.web.conf;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
public class FreemarkerConfig extends WebMvcConfigurerAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(FreemarkerConfig.class);

    private final
    SettingService settingService;

    @Autowired
    public FreemarkerConfig(SettingService settingService) {
        this.settingService = settingService;
    }

    @Bean
    public FreeMarkerConfigurer loadPathConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        String path = settingService.get(CmsConst.SKIN_PATH_SETTING);
        String DEFAULT_SKIN_PATH = "classpath:templates";
        if (!path.isEmpty()) {
            configurer.setTemplateLoaderPaths(DEFAULT_SKIN_PATH, path);
            LOGGER.info("Gangster CMS : templates path = {},{}", DEFAULT_SKIN_PATH, path);
        } else {
            configurer.setTemplateLoaderPath(DEFAULT_SKIN_PATH);
//            configurer.setTemplateLoaderPath("file:/home/bigmeng/Desktop/templates/");
        }

        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }
}
