package com.ganster.cms.auth.controller;


import com.ganster.cms.auth.dto.AjaxData;
import com.ganster.cms.auth.dto.GroupWithPermission;
import com.ganster.cms.core.dao.mapper.GroupPermissionMapper;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户组的增删改查
 */
@Controller
@RequestMapping("/groupL")
public class GroupController extends AjaxData{

    private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupPermissionMapper groupPermissionMapper;


    /**
     * 用户组的查询(包含权限)
     * @return
     * @throws GroupNotFountException
     */
    @RequestMapping(value = "/find")
    @ResponseBody
    public AjaxData getGroupList() throws GroupNotFountException {
        //查询所有的用户组
        GroupExample groupExample = new GroupExample();
        List<Group> groupList = groupService.selectByExample(groupExample);
        List<GroupWithPermission> gpList = new ArrayList<>();
        List<Permission> permissionList = null;
        for (Group group : groupList){
            permissionList= permissionService.selectByGroupId(group.getGroupId());
            List<String> permissionNameList = new ArrayList<>();
            if (permissionList!=null){
                for (Permission permission :permissionList){
                    permissionNameList.add(permission.getPermissionName());
                }
            }
            GroupWithPermission groupWithPermission = new GroupWithPermission(group,permissionNameList);
            gpList.add(groupWithPermission);
        }
        AjaxData ajaxData = new AjaxData();
        ajaxData.setMsg("success");
        ajaxData.setCount(gpList.size());
        ajaxData.setData((ArrayList) gpList);
    return ajaxData;
    }

    /**
     * 查看单个用户组
     * @param groupId
     */
    @RequestMapping("/find/{groupId}")
    @ResponseBody
    public Group listGroupById(@PathVariable("groupId") Integer groupId){
        if (groupId.equals(null)){
            LOGGER.info("用户组id为空");
        }
        Group group = groupService.selectByPrimaryKey(groupId);
        return group;
    }

    /**
     * 增加用户组
     * @param group
     */
    @RequestMapping("/add")
    @ResponseBody
    public AjaxData addGroup(@RequestBody Group group ){
        System.out.println("groupaaaaaaa"+group);
        int flag = groupService.insert(group);
        if (flag==1){return new AjaxData(0,"success",0,null);}
        else {
            return new AjaxData(0,"false",0,null);
        }
    }

    /**
     * 更新用户组
     * @param groupId
     * @param group
     * @return
     */
    @RequestMapping("/update/{groupId}")
    @ResponseBody
    public AjaxData updateGroup(@PathVariable("groupId") Integer groupId,@RequestBody Group group){
        group.setGroupId(groupId);
        int flag=0;
        flag = groupService.updateByPrimaryKey(group);
        if (flag==1){return new AjaxData(0,"success",0,null);}
        return  new AjaxData(-1,"false",0,null);
    }

    /**
     * 删除用户组
     * @param groupId
     */
    @RequestMapping("/delete/{groupId}")
    @ResponseBody
    public void deleteGroup(@PathVariable("groupId") Integer groupId){
        if (groupService.selectByPrimaryKey(groupId) != null){
            LOGGER.info("++++++++++++++delete"+groupId+"+++++++++++++++");
            groupService.deleteGroup(groupId);
        }
    }


}
