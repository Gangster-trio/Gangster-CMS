package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Create by Yoke on 2018/2/9
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    private UserService userService;

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
    public MessageDto judgeSite(@RequestParam Integer siteId) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(userId);
        if (!user.getUserIsAdmin()) {
            if (PermissionUtil.permittedSite(userId, siteId)) {
                return MessageDto.success(null);
            } else {
                return MessageDto.fail(2, "privilege");
            }
        } else {
            return MessageDto.success(null);
        }
    }
}
