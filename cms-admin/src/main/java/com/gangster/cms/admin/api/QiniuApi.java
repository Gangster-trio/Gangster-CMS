package com.gangster.cms.admin.api;

import com.gangster.cms.admin.config.QiniuConfig;
import com.qiniu.common.Zone;
import com.qiniu.common.ZoneReqInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QiniuApi {

    @Autowired
    QiniuConfig qiniuConfig;

    @GetMapping("/upToken")
    public String getUpToken() {
        return Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey()).uploadToken(qiniuConfig.getBucket());
    }

    @GetMapping("/upHttpAddr")
    public String getupAddr() {
        return Zone.autoZone().getUpHttp(new ZoneReqInfo(qiniuConfig.getAccessKey(),qiniuConfig.getBucket()));
    }

    @GetMapping("/upHttpsAddr")
    public String getUpHttpsAddr() {
        return Zone.autoZone().getUpHttps(new ZoneReqInfo(qiniuConfig.getAccessKey(),qiniuConfig.getBucket()));
    }

    @GetMapping("/cdnDomain")
    public String getCdnDomain(){
        return qiniuConfig.getCdnDomain();
    }
}
