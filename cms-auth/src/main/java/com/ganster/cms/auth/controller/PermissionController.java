package com.ganster.cms.auth.controller;

import com.ganster.cms.auth.dto.Message;
import com.ganster.cms.auth.dto.PermissionData;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add/category")
    public Message addCategoryPermission(@RequestBody PermissionData permissionData) {
        Message message = new Message();
        Group group = groupService.selectByPrimaryKey(permissionData.getGroupId());
        if (group != null) {
            Integer gid = permissionData.getGroupId();
            Integer cid = permissionData.getCategoryId();
            Integer sid = permissionData.getSiteId();
            List<String> pName = permissionData.getPermissionName();
            if (pName != null && !pName.isEmpty()) {
                for (String i : pName) {
                    permissionService.addCategoryPermissionToGroup(gid, sid, cid, i);
                    message.setMsg("添加权限成功");
                    message.setCode(0);
                }
            }
            message.setMsg("添加权限失败");
        }
        message.setMsg("添加权限失败");
        return message;
    }

    @RequestMapping("/add/model")
    public Message addModelPermission(@RequestBody PermissionData permissionData) {
        Message message = new Message();
        Group group = groupService.selectByPrimaryKey(permissionData.getGroupId());
        if (group != null) {
            Integer gid = permissionData.getGroupId();
            Integer mid = permissionData.getCategoryId();
            Integer sid = permissionData.getSiteId();
            List<String> pName = permissionData.getPermissionName();
            if (pName != null && !pName.isEmpty()) {
                for (String i : pName) {
                    permissionService.addModulePermissionToGroup(gid, sid, mid, i);
                    message.setMsg("添加权限成功");
                    message.setCode(0);
                }
            }
            message.setMsg("添加权限失败");
        }
        message.setMsg("添加权限失败");
        return message;
    }
}
