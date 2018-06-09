package com.gangster.cms.admin.service.auth;


import com.gangster.cms.admin.service.ModuleService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.admin.util.StringUtil;
import com.gangster.cms.common.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * Service 与权限有关的Service
 */
@Service
public class UserPermissionService {
    private static final Logger logger = LoggerFactory.getLogger(UserPermissionService.class);

    @Autowired
    PermissionService permissionService;
    @Autowired
    SiteService siteService;
    @Autowired
    ModuleService moduleService;

    public PageInfo<Permission> findUserPermission(Integer userId, Integer page, Integer limit) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andUserIdEqualTo(userId);
        PageInfo<Permission> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> permissionService.selectByExample(permissionExample));
        List<Permission> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return null;
        } else return pageInfo;
    }

    public Boolean deletePermission(Integer permissionId) {
        Permission permission = permissionService.selectByPrimaryKey(permissionId);
        return permission != null && permissionService.deleteByPrimaryKey(permissionId) == 1;
    }

    public List<Site> findSite() {
        SiteExample siteExample = new SiteExample();
        return siteService.selectByExample(siteExample);
    }

    public List<Module> findModel() {
        ModuleExample moduleExample = new ModuleExample();
        return moduleService.selectByExample(moduleExample);
    }

    public boolean addPermission(Integer siteId, Integer moduleId, Integer userId) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andUserIdEqualTo(userId);
        List<Permission> permissions = permissionService.selectByExample(permissionExample);
        for (Permission permission : permissions) {
            if (permission.getModuleId().equals(moduleId) && permission.getSiteId().equals(siteId)) {
                return false;
            }
        }
        Permission permission = new Permission();
        permission.setModuleId(moduleId);
        permission.setSiteId(siteId);
        permission.setUserId(userId);
        permissionService.insert(permission);
        return true;
    }

    public boolean batchDeleting(String permissionIdData) {
        return split(permissionIdData, permissionService);
    }

    private boolean split(String permissionIdData, PermissionService permissionService) {
        if (!StringUtil.isNullOrEmpty(permissionIdData)) {
            String[] permissionIds = permissionIdData.split(",");
            try {
                logger.info(permissionIds[1] + permissionIds[0]);
                Stream.of(permissionIds).forEach(e -> permissionService.deleteByPrimaryKey(Integer.parseInt(e)));
            } catch (Exception e) {
                logger.error("删除权限时发生错误");
                e.printStackTrace();
                return false;
            }
            return true;
        } else return false;
    }
}
