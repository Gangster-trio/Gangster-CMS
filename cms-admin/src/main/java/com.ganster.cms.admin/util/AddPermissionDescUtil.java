package com.ganster.cms.admin.util;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.ModuleService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.util.PermissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Util 为权限添加权限描述
 */
@Component
public class AddPermissionDescUtil {
    private static final Logger logger = LoggerFactory.getLogger(AddPermissionDescUtil.class);
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModuleService moduleService;

    private String pDesc;

    private String sDesc;

    private String cDesc;

    private String mDesc;

    public void setCategoryPermissionDesc(Integer sid, Integer cid, String pName) {
        List<Permission> permissionList = this.getCategoryPermission(sid, cid, pName);
        if (pName.equals(CmsConst.PERMISSION_READ)) {
            pDesc = "读权限";
        } else if (pName.equals(CmsConst.PERMISSION_WRITE)) {
            pDesc = "写权限";
        }
        Site site = siteService.selectByPrimaryKey(sid);
        Category category = categoryService.selectByPrimaryKey(cid);
        sDesc="对站点"+site.getSiteName()+"下的";
        cDesc=category.getCategoryTitle()+"栏目具有";
        String desc=sDesc+cDesc+pDesc;
        for (Permission i : permissionList) {
           i.setPermissionDesc(desc);
           permissionService.updateByPrimaryKey(i);
        }
    }

    public void setSitePermissionDesc(Integer sid) {
        List<Permission> permissionList = this.getSitePermission(sid);
        Site site=siteService.selectByPrimaryKey(sid);
        String desc="对站点"+site.getSiteName()+"具有控制权限";
        for (Permission i:permissionList){
            i.setPermissionDesc(desc);
            permissionService.updateByPrimaryKey(i);
        }
    }

    public void setModulePermissionDesc(Integer sid, Integer mid, String pName) {
        List<Permission> permissionList = this.getModulePermission(sid, mid, pName);
        if (pName.equals(CmsConst.PERMISSION_READ)) {
            pDesc = "读权限";
        } else if (pName.equals(CmsConst.PERMISSION_WRITE)) {
            pDesc = "写权限";
        }
        Site site = siteService.selectByPrimaryKey(sid);
        Module module=moduleService.selectByPrimaryKey(mid);
        sDesc="对站点"+site.getSiteName()+"下的";
        mDesc=module.getModuleName()+"模块具有";
        String desc=sDesc+mDesc+pDesc;
        for (Permission i:permissionList){
            i.setPermissionDesc(desc);
            permissionService.updateByPrimaryKey(i);
        }
    }

    private List<Permission> getCategoryPermission(Integer sid, Integer cid, String pName) {
        String permissionName = PermissionUtil.formatCategoryPermissionName(sid, cid, pName);
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionNameEqualTo(permissionName);
        return permissionService.selectByExample(permissionExample);
    }

    private List<Permission> getModulePermission(Integer sid, Integer mid, String pName) {
        String permissionName = PermissionUtil.formatModulePermissionName(sid, mid, pName);
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionNameEqualTo(permissionName);
        return permissionService.selectByExample(permissionExample);
    }

    private List<Permission> getSitePermission(Integer sid) {
        String permissionName = PermissionUtil.formatSitePermissionName(sid);
        logger.info("+++++++++++++++++++++++++++++"+permissionName+"++++++++++++++++++++++");
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andPermissionNameEqualTo(permissionName);
        return permissionService.selectByExample(permissionExample);
    }
}
