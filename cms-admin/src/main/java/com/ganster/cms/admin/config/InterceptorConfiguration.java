package com.ganster.cms.admin.config;

import com.ganster.cms.admin.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Yoke
 * Created on 2018/4/13
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration ir = registry.addInterceptor(permissionInterceptor());
        ir.addPathPatterns("/**").excludePathPatterns("/css/**", "/fonts/**", "/layui/**", "/login/**", "/module/**", "/tag/**", "/util/**");
        super.addInterceptors(registry);
    }
}
