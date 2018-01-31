package com.ganster.cms.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Create by Yoke on 2018/1/31
 */
@Component
@PropertySource(value = "classpath:imgconfig.yml")
@ConfigurationProperties(prefix = "ImgConfig")
public class ImgConfig {
    @Value("${saveLocation}")
    private String saveLocation;
    @Value("${maxUploadSizePerFile}")
    private String maxUploadSizePerFile;

    public String getSaveLocation() {
        return saveLocation;
    }

    public void setSaveLocation(String saveLocation) {
        this.saveLocation = saveLocation;
    }

    public String getMaxUploadSizePerFile() {
        return maxUploadSizePerFile;
    }

    public void setMaxUploadSizePerFile(String maxUploadSizePerFile) {
        this.maxUploadSizePerFile = maxUploadSizePerFile;
    }
}
