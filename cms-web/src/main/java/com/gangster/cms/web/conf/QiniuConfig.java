package com.gangster.cms.web.conf;

import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:qiniu.properties")
@ConfigurationProperties("qiniu")
public class QiniuConfig {

    private String bucket;

    private String accessKey;

    private String secretKey;

    private String cdnDomain;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCdnDomain() {
        return cdnDomain;
    }

    public void setCdnDomain(String cdnDomain) {
        this.cdnDomain = cdnDomain;
    }

    public Auth getAuth() {
        return Auth.create(accessKey, secretKey);
    }

    public BucketManager getBucketManager() {
        return new BucketManager(getAuth(), new Configuration(Zone.autoZone()));
    }
}
