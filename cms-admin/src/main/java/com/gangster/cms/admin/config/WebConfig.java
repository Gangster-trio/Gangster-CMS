package com.gangster.cms.admin.config;

import com.gangster.cms.admin.interceptor.LoginInterceptor;
import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.common.constant.CmsConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yoke
 * Created on 2018/4/17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SettingService settingService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**", "/fonts/**", "/js/**", "/layui/**", "/login/**", "/util/**", "/pic/**", "/tag/**", "/file/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:" + settingService.get(CmsConst.PIC_PATH_SETTING)).setCachePeriod(1);
        registry.addResourceHandler("/webfile/**").addResourceLocations("file:" + settingService.get(CmsConst.FILE_PATH)).setCachePeriod(1);
    }
}
