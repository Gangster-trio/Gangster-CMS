package com.ganster.cms.core.util;

import java.util.Map;
import java.util.Set;

public class PermissionUtil {

    private static Map<Integer, Set<String>> permissionMap;

    private static void flush(Integer uid) {

    }

    private static Boolean permitted(Integer uid, String pName) {

        return false;
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
