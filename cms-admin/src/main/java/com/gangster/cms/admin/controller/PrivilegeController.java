package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.util.PermissionUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.User;
import org.springframework.web.bind.annotation.*;


/**
 * Create by Yoke on 2018/2/9
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @GetMapping("/category")
    public MessageDto judgeCategory(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam Integer siteId, @RequestParam Integer categoryId) {
        if (!user.getUserIsAdmin()) {
            if (PermissionUtil.permittedCategory(user.getUserId(), siteId, categoryId, CmsConst.PERMISSION_WRITE)) {
                return MessageDto.success(null);
            } else {
                return MessageDto.fail(2, "no privilege");
            }
        } else {
            return MessageDto.success(null);
        }
    }

    @GetMapping("/site")
    public MessageDto judgeSite(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam Integer siteId) {
        if (!user.getUserIsAdmin()) {
            if (PermissionUtil.permittedSite(user.getUserId(), siteId)) {
                return MessageDto.success(null);
            } else {
                return MessageDto.fail(2, "no privilege");
            }
        } else {
            return MessageDto.success(null);
        }
    }
}
