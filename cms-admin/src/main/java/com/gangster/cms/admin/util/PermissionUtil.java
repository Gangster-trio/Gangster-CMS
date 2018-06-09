package com.gangster.cms.admin.util;

import com.gangster.cms.admin.service.ModuleService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.common.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 工具类   权限转化权限描述
 */
@Component
public class PermissionUtil {
    private static final Logger logger = LoggerFactory.getLogger(PermissionUtil.class);
    private static PermissionUtil factory;
    @Autowired
    private SiteService siteService;
    @Autowired
    private ModuleService moduleService;

    /**
     * 对权限添加描述
     *
     * @param permission 所含有的权限
     * @return 权限的描述
     */
    public static String permissionDesc(Permission permission) {
        SiteExample siteExample = new SiteExample();
        siteExample.or().andSiteIdEqualTo(permission.getSiteId());
        Site site = factory.siteService.selectByExample(siteExample).get(0);
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.or().andModuleIdEqualTo(permission.getModuleId());
        Module module = factory.moduleService.selectByExample(moduleExample).get(0);
        return "对站点       " + site.getSiteName() + "       下的       " + module.getModuleName() + "       模块具有操作权限";
    }

    /**
     * 获得权限名
     *
     * @param permission 所含有的权限
     * @return 权限名
     */
    public static String permissionName(Permission permission) {
        return permission.getSiteId() + ":" + permission.getModuleId();
    }

    @PostConstruct
    private void init() {
        factory = this;
    }
}
