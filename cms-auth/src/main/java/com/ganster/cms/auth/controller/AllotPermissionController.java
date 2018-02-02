package com.ganster.cms.auth.controller;

import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.PermissionNotFoundException;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.pojo.PermissionExample;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class AllotPermissionController {
    private static final Logger logger= LoggerFactory.getLogger(AllotPermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupService groupService;

    @RequestMapping("/add")
    @RequiresPermissions("add")
    public void addPermission(@RequestParam("GroupName") String groupName,@RequestParam("PermissionName") String permissionName) throws PermissionNotFoundException, GroupNotFountException {
        PermissionExample permissionExample=new PermissionExample();
        permissionExample.createCriteria().andPermissionNameEqualTo(permissionName);
        List<Permission> permissions=permissionService.selectByExample(permissionExample);
        if (permissions==null) throw new PermissionNotFoundException();
        for (Permission i:permissions){
//            groupService.addPermissionToGroup(i.getPermissionName(),groupName);
        }
    }

    @RequestMapping("/delect")
    @RequiresPermissions("delect")
    public int delectPermission(@RequestParam("PermissionId") Integer permissionId){
      return  permissionService.deleteByPrimaryKey(permissionId);
    }
}
