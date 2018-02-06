package com.ganster.cms.auth.controller;

import com.ganster.cms.auth.dto.AjaxData;
import com.ganster.cms.auth.dto.Message;
import com.ganster.cms.auth.dto.PermissionData;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModuleService moduleService;


    @PostMapping("/addpermission")
    public Message addPermission(@RequestBody PermissionData permissionData) {
        Message message = new Message();
        Group group = groupService.selectByPrimaryKey(permissionData.getGroupId());
        if (group != null) {
            Integer gid = permissionData.getGroupId();
            Integer cid = permissionData.getCategoryId();
            Integer sid = permissionData.getSiteId();
            List<String> pName = permissionData.getPermissionName();
            Integer mid = permissionData.getMoudleId();
            if (pName != null && !pName.isEmpty()) {
                for (String i : pName) {
                    if (i.equals("READ")){
                        i=CmsConst.PERMISSION_READ;
                        permissionService.addCategoryPermissionToGroup(gid, sid, cid, i);
                    }else if (i.equals("WRITE")){
                        i=CmsConst.PERMISSION_WRITE;
                        permissionService.addCategoryPermissionToGroup(gid, sid, cid, i);
                    }
                }
                message.setMsg("添加权限成功");
                message.setCode(0);
            }
            if (mid != null && pName != null && !pName.isEmpty()) {
                for (String i : pName) {
                    if (i.equals("READ")) {
                        i = CmsConst.PERMISSION_READ;
                        permissionService.addModulePermissionToGroup(gid, sid, mid, i);
                    } else if (i.equals("WRITE")) {
                        i = CmsConst.PERMISSION_WRITE;
                        permissionService.addModulePermissionToGroup(gid, sid, mid, i);
                    }
                }
                message.setMsg("添加权限成功");
                message.setCode(0);
            }
        }
        message.setMsg("添加权限失败");
        return message;
    }

    @GetMapping("/findsite")
    public AjaxData findSite() {
        AjaxData ajaxData = new AjaxData();
        SiteExample siteExample = new SiteExample();
        List<Site> siteList = siteService.selectByExample(siteExample);
        ajaxData.setCode(siteList.size());
        ajaxData.setData((ArrayList) siteList);
        return ajaxData;
    }

    @GetMapping("/findcategory/{SiteId}")
    public AjaxData findCategory(@PathVariable("SiteId") Integer siteId) {
        AjaxData ajaxData = new AjaxData();
        Site site =siteService.selectByPrimaryKey(siteId);
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andCategorySiteIdEqualTo(site.getSiteId());
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        ajaxData.setCode(categoryList.size());
        ajaxData.setData((ArrayList) categoryList);
        return ajaxData;
    }

    @GetMapping("/findmodel")
    public AjaxData findModel() {
        AjaxData ajaxData = new AjaxData();
        ModuleExample moduleExample = new ModuleExample();
        List<Module> moduleList = moduleService.selectByExample(moduleExample);
        ajaxData.setCode(moduleList.size());
        ajaxData.setData((ArrayList) moduleList);
        return ajaxData;
    }

}
