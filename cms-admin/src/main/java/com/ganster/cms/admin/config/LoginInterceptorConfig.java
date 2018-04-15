package com.ganster.cms.admin.config;

import com.ganster.cms.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yoke
 * Created on 2018/4/15
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**", "/fonts/**", "/js/**", "/layui/**", "/login/**","/util/**", "/pic/**","/tag/**");
    }
}
