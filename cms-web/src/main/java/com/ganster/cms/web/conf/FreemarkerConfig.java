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

    @Autowired
    SettingService settingService;

    @Bean
    public FreeMarkerConfigurer loadPathConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        String path = settingService.get(CmsConst.SKIN_PATH_SETTING);
        if (!path.isEmpty()) {
            configurer.setTemplateLoaderPath(path);
            LOGGER.info("Gangster CMS : templates path = {}", path);
        } else {
            configurer.setTemplateLoaderPath("classpath:templates");
//            configurer.setTemplateLoaderPath("file:/home/bigmeng/Desktop/templates/");
        }

        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }
}
