package com.ganster.cms.admin.util;

import org.apache.shiro.SecurityUtils;

/**
 * Create by Yoke on 2018/2/4
 */
public class UserUtil {
    public static Integer getCurrentUserId(){
        return (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
    }
}
