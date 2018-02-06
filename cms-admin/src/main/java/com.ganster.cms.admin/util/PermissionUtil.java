package com.ganster.cms.admin.util;

import java.util.Map;
import java.util.Set;

public class PermissionUtil {
    private static Map<Integer,Set<String>> permissionMap;
    private static void flush(Integer uid){

    }

    private static Boolean permitted(Integer uid,String pName){

        return false;
    }

    private static Boolean permitted(Integer uid,Integer sid,Integer cid,String p){

        return false;
    }

    private static Boolean permitted(Integer uid,Integer sid){

        return false;
    }
}
