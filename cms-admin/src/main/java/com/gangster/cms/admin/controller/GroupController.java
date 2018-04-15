package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.GroupWithPermission;
import com.gangster.cms.admin.dto.Message;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.gangster.cms.common.pojo.Group;
import com.gangster.cms.common.pojo.GroupExample;
import com.gangster.cms.common.pojo.Permission;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    private static GroupController factory;


    @PostConstruct
    public void init() {
        factory = this;
    }

    private static List<GroupWithPermission> gpList = new ArrayList<>();

    public static List<GroupWithPermission> getGpList() {
        return gpList;
    }

    private static void setGpList(List<GroupWithPermission> gpList) {
        GroupController.gpList = gpList;
    }

    public static void refresh() {
        List<GroupWithPermission> newGplist = new ArrayList<>();
        GroupExample groupExample = new GroupExample();
        List<Group> groupList = factory.groupService.selectByExample(groupExample);
        Set<Permission> permissionSet = new HashSet<>();
        for (Group group : groupList) {
            permissionSet = factory.permissionService.selectByGroupId(group.getGroupId());
            List<Permission> permissionList = new ArrayList<>(permissionSet);
            List<String> permissionNameList = new ArrayList<>();
            if (permissionList != null) {
                for (Permission permission : permissionList) {
                    String permissionNames = permission.getPermissionName();
                    permissionNameList.add(permissionNames);
                }
            }
            GroupWithPermission groupWithPermission = new GroupWithPermission(group, permissionNameList);
            newGplist.add(groupWithPermission);
            GroupController.setGpList(newGplist);
        }
    }

    /**
     * 用户组的查询(包含权限)
     *
     * @return AjaxData
     * @throws GroupNotFountException 用户组未找到
     */
    @GetMapping(value = "/find")
    @ResponseBody
    public AjaxData getGroupList() throws GroupNotFountException {
        //查询所有的用户组
        AjaxData ajaxData = new AjaxData();
        ajaxData.setMsg("success");
        ajaxData.setCount(gpList.size());
        ajaxData.setData(gpList);
        return ajaxData;
    }

    /**
     * 查看单个用户组
     *
     * @param groupId 用户组id
     */
    @GetMapping("/find/{groupId}")
    @ResponseBody
    public Group listGroupById(@PathVariable("groupId") Integer groupId) {
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
    @PostMapping("/add")
    @ResponseBody
    public Message addGroup(@RequestBody Group group) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> list = groupService.selectByUserId(userId);
        for (Group flag : list) {
            if ("admin".equals(flag.getGroupName())) {
                int count = groupService.insert(group);
                if (count == 0) {
                    return super.buildMessage(1, "false", null);
                }
                if (count == 1) {
                    PermissionUtil.flush(userId);
                    GroupController.refresh();
                    return super.buildMessage(0, "success", count);
                }
                break;
            }
        }
        PermissionUtil.flush(userId);
        GroupController.refresh();
        return super.buildMessage(2, "no privilege", null);
    }

    /**
     * 更新用户组
     *
     * @param groupId 用户组id
     * @param group   用户组对象
     * @return AjaxData
     */
    @PostMapping("/update/{groupId}")
    @ResponseBody
    public Message updateGroup(@PathVariable("groupId") Integer groupId, @RequestBody Group group) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> list = groupService.selectByUserId(userId);
        for (Group flag : list) {
            if ("admin".equals(flag.getGroupName())) {
                group.setGroupId(groupId);
                int count = groupService.updateByPrimaryKeySelective(group);
                GroupController.refresh();
                if (count == 0) {
                    return super.buildMessage(1, "false", null);
                }
                if (count == 1) {
                    PermissionUtil.flush(userId);
                    return super.buildMessage(0, "success", count);
                }
                break;
            }
        }

        PermissionUtil.flush(userId);
        GroupController.refresh();
        return super.buildMessage(2, "no privilege", null);
    }

    /**
     * 删除用户组
     *
     * @param groupId 用户组id
     */
    @GetMapping("/delete/{groupId}")
    @ResponseBody
    public void deleteGroup(@PathVariable("groupId") Integer groupId) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        List<Group> list = groupService.selectByUserId(userId);
        for (Group flag : list) {
            if ("admin".equals(flag.getGroupName())) {
                groupService.deleteGroup(groupId);
                GroupController.refresh();
                break;
            }
        }
        PermissionUtil.flush((Integer) SecurityUtils.getSubject().getSession().getAttribute("id"));
    }
}

