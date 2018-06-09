package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.dto.PermissionDTO;
import com.gangster.cms.admin.service.auth.UserPermissionService;
import com.gangster.cms.admin.util.PermissionUtil;
import com.gangster.cms.common.pojo.Module;
import com.gangster.cms.common.pojo.Permission;
import com.gangster.cms.common.pojo.Site;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * controller  与权限有关的Controller
 */
@RestController
@RequestMapping("/permission")
public class NewPermissionController {
    private static final Logger logger = LoggerFactory.getLogger(NewPermissionController.class);

    @Autowired
    private UserPermissionService permissionService;

    /**
     * 通过用户Id，查找用户所含有的权限
     *
     * @param userId 用户ID
     * @param page   所需信息的页数
     * @param limit  所需信息的条数
     * @return 用户所含有的所有权限
     */
    @SystemControllerLog(description = "查找用户所含有的权限")
    @GetMapping("/findPermission/{userId}")
    public AjaxData findPermission(
            @PathVariable("userId") Integer userId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer limit
    ) {
        PageInfo<Permission> pageInfo = permissionService.findUserPermission(userId, page, limit);
        if (pageInfo == null) {
            return new AjaxData(1, "无信息", 0, null);
        }
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (Permission permission : pageInfo.getList()) {
            PermissionDTO permissionDTO = new PermissionDTO(permission.getPermissionId(), PermissionUtil.permissionName(permission), PermissionUtil.permissionDesc(permission));
            permissionDTOS.add(permissionDTO);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), permissionDTOS);
    }

    /**
     * 删除单个权限
     *
     * @param permissionId 权限Id
     * @return 删除后的信息
     */
    @SystemControllerLog(description = "删除单个权限")
    @GetMapping("/deletePermission/{permissionId}")
    public MessageDto deletePermission(
            @PathVariable("permissionId") Integer permissionId
    ) {
        Boolean status = permissionService.deletePermission(permissionId);
        if (status) return MessageDto.success(null);
        else return MessageDto.fail(1, "删除权限失败");
    }

    /**
     * 查找所有的站点
     *
     * @return 所有的站点
     */
    @SystemControllerLog(description = "查找所有的站点")
    @GetMapping("/findSite")
    public AjaxData findSite() {
        List<Site> site = permissionService.findSite();
        return new AjaxData(0, "success", site.size(), site);
    }

    /**
     * 查找所有的模块
     *
     * @return 所有的模块
     */
    @SystemControllerLog(description = "查找所有的模块")
    @GetMapping("/findModule")
    public AjaxData findModel() {
        List<Module> modules = permissionService.findModel();
        return new AjaxData(0, "success", modules.size(), modules);
    }

    /**
     * 添加权限
     *
     * @param siteId   站点Id
     * @param moduleId 模块Id
     * @param userId   用户Id
     * @return 添加状态
     */
    @SystemControllerLog(description = "添加权限")
    @GetMapping("/addPermission/{siteId}/{moduleId}/{userId}")
    public MessageDto addPermission(
            @PathVariable("siteId") Integer siteId,
            @PathVariable("moduleId") Integer moduleId,
            @PathVariable("userId") Integer userId
    ) {
        boolean status = permissionService.addPermission(siteId, moduleId, userId);
        if (status) {
            return MessageDto.success(null);
        } else return MessageDto.fail(1, "权限已存在");
    }

    /**
     * 批量删除权限
     *
     * @param permissionIdData 权限Id字符串
     * @return 处理信息
     */
    @SystemControllerLog(description = "批量删除")
    @PostMapping("/batchDeleting")
    public MessageDto batchDeleting(String permissionIdData) {
        if (permissionService.batchDeleting(permissionIdData)) {
            return MessageDto.success(null);
        } else return MessageDto.fail(1, "批量删除失败");
    }

}
