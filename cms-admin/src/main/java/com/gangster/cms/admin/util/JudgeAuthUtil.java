package com.gangster.cms.admin.util;

import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.common.pojo.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 *   Util 判断权限是否存在
 */
@Component
public class JudgeAuthUtil {
    private static final Logger logger = LoggerFactory.getLogger(JudgeAuthUtil.class);
    @Autowired
    private PermissionService permissionService;

    public Boolean judgeAuthIsNull(Integer gid, String pName) {
        Set<Permission> permissionList = permissionService.selectByGroupId(gid);
        for (Permission i : permissionList) {
            if (i.getPermissionName().equals(pName)) {
                return false;
            }
        }
        return true;
    }
}
