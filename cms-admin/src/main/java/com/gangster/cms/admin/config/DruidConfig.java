package com.gangster.cms.admin.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfig {

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {

        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //登录查看信息的账号密码.

        servletRegistrationBean.addInitParameter("loginUsername", "admin");

        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
