package com.ganster.cms.core.util;

import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Component
public class PermissionUtil {
    private static final Logger logger = LoggerFactory.getLogger(PermissionUtil.class);
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private GroupService groupService;

    private static PermissionUtil permissionUtil;

    public static Map<Integer, Set<String>> permissionMap;

    @PostConstruct
    public void init() {
        permissionUtil = this;
        permissionUtil.permissionService = this.permissionService;
        permissionUtil.userService = this.userService;
        permissionUtil.groupService = this.groupService;
        permissionMap = new HashMap<>();
    }


    public static List<Permission> flush(Integer uid) {
        List<Permission> permissions = null;
        try {
            permissions = permissionUtil.permissionService.selectByUserId(uid);
        } catch (GroupNotFountException e) {
            logger.info("Current userId: {} has no permission", uid);
            return null;
        }
        Set<String> permissionName = new HashSet<>();
        for (Permission i : permissions) {
            permissionName.add(i.getPermissionName());
        }

        permissionMap.put(uid, permissionName);
        return permissions;
    }

    private static Boolean permitted(Integer uid, String pName) {
        flush(uid);
        Set<String> set = permissionMap.get(uid);
        return set != null && set.contains(pName);
    }

    public static Boolean permittedCategory(Integer uid, Integer sid, Integer cid, String p) {
        return permitted(uid, formatCategoryPermissionName(sid, cid, p));
    }

    public static Boolean permittedSite(Integer uid, Integer sid) {
        return permitted(uid, formatSitePermissionName(sid));
    }

    public static Boolean permittedModule(Integer uid, Integer sid, Integer mid, String p) {
        return permitted(uid, formatModulePermissionName(sid, mid, p));
    }


    public static String formatCategoryPermissionName(Integer sid, Integer cid, String pName) {
        //for example | "Site(1):Category(23):Permission(view)
        return "Site(" + sid + ")" + ":Category(" + cid + "):Permission(" + pName + ")";
    }

    public static String formatModulePermissionName(Integer sid, Integer moduleId, String pName) {
        //for example | "Site(1):Module(23):Permission(view)
        return "Site(" + sid + ")" + ":Module(" + moduleId + "):Permission(" + pName + ")";
    }

    public static String formatSitePermissionName(Integer sid) {
        return "Site(" + sid + ")";
    }
}
