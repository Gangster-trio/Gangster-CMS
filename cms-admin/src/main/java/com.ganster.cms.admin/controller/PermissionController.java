package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.admin.dto.PermissionData;
import com.ganster.cms.admin.util.AddPermissionDescUtil;
import com.ganster.cms.admin.util.JudgeAuthUtil;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller     与权限有关的所有操作
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {
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
    @Autowired
    private AddPermissionDescUtil addPermissionDescUtil;
    @Autowired
    private JudgeAuthUtil judgeAuthUtil;

    /**
     * 为用户组添加权限
     *
     * @param
     * @return  Message  权限是否添加成功
     */
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
            if (sid == null || pName == null) return null;
            if (judgeAuthUtil.judgeAuthIsNull(gid, PermissionUtil.formatSitePermissionName(sid))) {
                permissionService.addSitePermissionToGroup(sid, gid);
                addPermissionDescUtil.setSitePermissionDesc(sid);
            }
            if (pName != null && !pName.isEmpty() && cid!=null&&sid!=null) {
                for (String i : pName) {
                    if (i.equals("READ")){
                        if (judgeAuthUtil.judgeAuthIsNull(gid, PermissionUtil.formatCategoryPermissionName(sid, cid, i))) {
                            i = CmsConst.PERMISSION_READ;
                            permissionService.addCategoryPermissionToGroup(gid, sid, cid, i);
                            addPermissionDescUtil.setCategoryPermissionDesc(sid, cid, i);
                        }
                    }else if (i.equals("WRITE")){
                        if (judgeAuthUtil.judgeAuthIsNull(gid, PermissionUtil.formatCategoryPermissionName(sid, cid, i))) {
                            i = CmsConst.PERMISSION_WRITE;
                            permissionService.addCategoryPermissionToGroup(gid, sid, cid, i);
                            addPermissionDescUtil.setCategoryPermissionDesc(sid, cid, i);
                        }
                    }
                }
                message.setMsg("添加权限成功");
                message.setCode(0);
            }
            if (sid != null && mid != null && pName != null && !pName.isEmpty()) {
                for (String i : pName) {
                    if (i.equals("READ")) {
                        if (judgeAuthUtil.judgeAuthIsNull(gid, PermissionUtil.formatModulePermissionName(sid, mid, i))) {
                            i = CmsConst.PERMISSION_READ;
                            permissionService.addModulePermissionToGroup(gid, sid, mid, i);
                            addPermissionDescUtil.setModulePermissionDesc(sid, mid, i);
                        }
                    } else if (i.equals("WRITE")) {
                        if (judgeAuthUtil.judgeAuthIsNull(gid, PermissionUtil.formatModulePermissionName(sid, mid, i))) {
                            i = CmsConst.PERMISSION_WRITE;
                            permissionService.addModulePermissionToGroup(gid, sid, mid, i);
                            addPermissionDescUtil.setModulePermissionDesc(sid, mid, i);
                        }
                    }
                }
                message.setMsg("添加权限成功");
                message.setCode(0);
            }
        }
        message.setMsg("添加权限失败");
        return message;
    }

    /**
     *  通过角色组ID，查找它所拥有的权限
     *
     * @param groupId
     * @param page
     * @param limit
     * @return    查找到的权限
     */
    @GetMapping("/findpermission/{GroupId}")
    public AjaxData findPermission(@PathVariable("GroupId") Integer groupId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null || page == 0) {
            page = 1;
        }
        if (limit == null || limit == 0) {
            limit = 10;
        }
        List<Permission> list = new ArrayList<>();
        Integer j = 0;
        try {
            list = permissionService.selectByGroupId(groupId);
            for (int i = 0; i < list.size(); i++) {
                j++;
            }
            return super.buildAjaxData(0, "true", j, list);
        } catch (GroupNotFountException e) {
            return super.buildAjaxData(1, "false", 0, null);
        }
    }

    /**
     * 通过权限Id删除权限
     *
     * @param permissionId
     * @return int   删除的数量
     */
    @GetMapping("/deletepermission/{PermissionId}")
    public int deletePermission(@PathVariable("PermissionId") Integer permissionId) {
        Permission permission = permissionService.selectByPrimaryKey(permissionId);
        if (permission != null) {
            permissionService.deletePermission(permissionId);
            return 1;
        } else
            return 0;
    }

    /**
     * 添加权限时，查找所有站点
     *
     * @return AjaxData 查找到的所有站点
     */

    @GetMapping("/findsite")
    public AjaxData findSite() {
        AjaxData ajaxData = new AjaxData();
        SiteExample siteExample = new SiteExample();
        List<Site> siteList = siteService.selectByExample(siteExample);
        ajaxData.setCode(siteList.size());
        ajaxData.setData((ArrayList) siteList);
        return ajaxData;
    }

    /**
     * 添加权限时，查找所有栏目
     *
     * @param siteId
     * @return  AjaxData 查找到的所有栏目
     */
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

    /**
     *
     * 添加权限时，查找所有模块
     * @return  AjaxData 查找到的所有模块
     */
    @GetMapping("/findmodel")
    public AjaxData findModel() {
        AjaxData ajaxData = new AjaxData();
        ModuleExample moduleExample = new ModuleExample();
        List<Module> moduleList = moduleService.selectByExample(moduleExample);
        ajaxData.setCode(moduleList.size());
        ajaxData.setData((ArrayList) moduleList);
        return ajaxData;
    }

    /**
     * 查找所有角色组
     *
     * @param groupId
     * @return  AjaxData 查找到的所有角色组
     */
    @GetMapping("/findGroupName/{GroupId}")
    public AjaxData findGroup(@PathVariable("GroupId") Integer groupId) {
        AjaxData ajaxData = new AjaxData();
        Group group = groupService.selectByPrimaryKey(groupId);
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        ajaxData.setData(groupList);
        return ajaxData;
    }
}
