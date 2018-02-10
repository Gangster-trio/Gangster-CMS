//package com.ganster.cms.auth.util;
//
//import com.ganster.cms.core.exception.GroupNotFountException;
//import com.ganster.cms.core.pojo.Permission;
//import com.ganster.cms.core.service.PermissionService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// *   Util 判断权限是否存在
// */
//@Component
//public class JudgeAuthUtil {
//    private static final Logger logger = LoggerFactory.getLogger(JudgeAuthUtil.class);
//    @Autowired
//    private PermissionService permissionService;
//
//    public Boolean judgeAuthIsNull(Integer gid, String pName) {
//        try {
//            List<Permission> permissionList = permissionService.selectByGroupId(gid);
//            for (Permission i : permissionList) {
//                if (i.getPermissionName().equals(pName)) {
//                    return false;
//                }
//            }
//            return true;
//        } catch (GroupNotFountException e) {
//            return false;
//        }
//    }
//}
