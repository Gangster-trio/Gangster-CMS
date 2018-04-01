package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.GroupWithPermission;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户组的增删改查
 */
@Controller
@RequestMapping("/groupL")
public class GroupController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupService groupService;


    /**
     * 用户组的查询(包含权限)
     * @return AjaxData
     */
    @RequestMapping(value = "/find")
    @ResponseBody
    public AjaxData getGroupList() {
        //查询所有的用户组
        GroupExample groupExample = new GroupExample();
        List<Group> groupList = groupService.selectByExample(groupExample);
        List<GroupWithPermission> gpList = new ArrayList<>();
        Set<Permission> permissionList;
        for (Group group : groupList) {
            permissionList = permissionService.selectByGroupId(group.getGroupId());
            List<String> permissionNameList = new ArrayList<>();
            if (permissionList!=null){
                for (Permission permission :permissionList){
//                    String permissionNames = permission.getPermissionName() + "<br/>";
                    String permissionNames = permission.getPermissionName();
                    permissionNameList.add(permissionNames);
                }
            }
            GroupWithPermission groupWithPermission = new GroupWithPermission(group, permissionNameList);
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
     * @param groupId  用户组id
     */
    @RequestMapping("/find/{groupId}")
    @ResponseBody
    public Group listGroupById(@PathVariable("groupId") Integer groupId){
        if (groupId == null) {
            LOGGER.info("用户组id为空");
        }
        return groupService.selectByPrimaryKey(groupId);
    }

    /**
     * 增加用户组
     *
     * @param group 用户组
     */
    @RequestMapping("/add")
    @ResponseBody
    public Message addGroup(@RequestBody Group group) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> list = groupService.selectByUserId(userId);
        for (Group flag : list) {
            if (flag.getGroupName().equals("admin")) {
                int count = groupService.insert(group);
                if (count == 0) {
                    return super.buildMessage(1, "false", null);
                }
                if (count == 1) {
                    return super.buildMessage(0, "success", count);
                }
                break;
            }
        }
        PermissionUtil.flush(userId);
        return super.buildMessage(2, "no privilege", null);
    }

    /**
     * 更新用户组
     * @param groupId  用户组id
     * @param group   用户组对象
     * @return AjaxData
     */
    @RequestMapping("/update/{groupId}")
    @ResponseBody
    public Message updateGroup(@PathVariable("groupId") Integer groupId, @RequestBody Group group) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> list = groupService.selectByUserId(userId);
        for (Group flag : list) {
            if (flag.getGroupName().equals("admin")) {
                group.setGroupId(groupId);
                int count = groupService.updateByPrimaryKeySelective(group);
                if (count == 0) {
                    return super.buildMessage(1, "false", null);
                }
                if (count == 1) {
                    return super.buildMessage(0, "success", count);
                }
                break;
            }
        }
        PermissionUtil.flush(userId);
        return super.buildMessage(2, "no privilege", null);
    }

    /**
     * 删除用户组
     * @param groupId   用户组id
     */
    @RequestMapping("/delete/{groupId}")
    @ResponseBody
    public void deleteGroup(@PathVariable("groupId") Integer groupId) {


        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> list = groupService.selectByUserId(userId);
        for (Group flag : list) {
            if (flag.getGroupName().equals("admin")) {
                groupService.deleteGroup(groupId);
                break;
            }
        }
        LOGGER.info("++++++++++++++delete" + groupId + "+++++++++++++++");
        PermissionUtil.flush((Integer) SecurityUtils.getSubject().getSession().getAttribute("id"));
    }
}

