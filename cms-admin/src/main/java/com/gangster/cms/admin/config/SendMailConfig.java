package com.gangster.cms.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "sendmail", ignoreUnknownFields = false)
@PropertySource("classpath:/sendmail.properties")
@Component
public class SendMailConfig {
    private String mailFromMailAddr;

    public String getMailFromMailAddr() {
        return mailFromMailAddr;
    }

    public void setMailFromMailAddr(String mailFromMailAddr) {
        this.mailFromMailAddr = mailFromMailAddr;
    }

}
