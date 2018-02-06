package com.ganster.cms.core.util;

/**
 * Create by Yoke on 2018/2/5
 */
public class PermissionUtil {
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
