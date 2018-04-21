package com.gangster.cms.admin.util;

import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.common.pojo.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class PermissionUtil {
    private static final Logger logger = LoggerFactory.getLogger(PermissionUtil.class);
    @Resource
    private PermissionService permissionService;

    private static PermissionUtil permissionUtil;

    private static Map<Integer, Set<String>> permissionMap;

    public static List<Integer> getAllPermittedCategory(Integer uid, Integer sid, String per) {
        if (permissionMap.get(uid) == null) {
            if (flush(uid) == null) {
                return new ArrayList<>();
            }
        }
        Set<String> permissionSet = permissionMap.get(uid);
        Pattern pattern = Pattern.compile("Site\\(" + sid + "\\):Category\\((?<id>\\d+)\\):Permission\\(" + per + "\\)");
        return permissionSet.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> Integer.parseInt(matcher.group("id")))
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> getAllPermissionSite(Integer uid) {
        if (permissionMap.get(uid) == null) {
            if (flush(uid) == null) {
                return new ArrayList<>();
            }
        }
        Set<String> permissionSet = permissionMap.get(uid);

        Pattern pattern = Pattern.compile("Site\\((?<id>\\d+)\\)");
        return permissionSet.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> Integer.parseInt(matcher.group("id")))
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> getAllPermissionModule(Integer uid, Integer sid, String per) {
        if (permissionMap.get(uid) == null) {
            if (flush(uid) == null) {
                return new ArrayList<>();
            }
        }
        Set<String> permissionSet = permissionMap.get(uid);

        Pattern pattern = Pattern.compile("Site\\(" + sid + "\\):Module\\((?<id>\\d+)\\):Permission\\(" + per + "\\)");
        return permissionSet.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> Integer.parseInt(matcher.group("id")))
                .distinct()
                .collect(Collectors.toList());
    }


    public static List<Permission> flush(Integer uid) {
        long s = System.currentTimeMillis();
        logger.info("start flush permission...");
        List<Permission> permissions;
        permissions = permissionUtil.permissionService.selectByUserId(uid);
        Set<String> permissionName = new HashSet<>();
        for (Permission i : permissions) {
            permissionName.add(i.getPermissionName());
        }

        permissionMap.put(uid, permissionName);
        logger.info("flush succeed!  time:{}ms", System.currentTimeMillis() - s);
        return permissions;
    }

    private static Boolean permitted(Integer uid, String pName) {
        if (permissionMap.get(uid) == null) {
            flush(uid);
        }
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

    @PostConstruct
    private void init() {
        permissionUtil = this;
        permissionUtil.permissionService = this.permissionService;
        permissionMap = new ConcurrentHashMap<>();
    }
}