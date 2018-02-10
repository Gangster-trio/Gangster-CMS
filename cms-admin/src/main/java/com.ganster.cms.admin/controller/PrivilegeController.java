package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Create by Yoke on 2018/2/9
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController extends BaseController {
    @Autowired
    private SiteService siteService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @GetMapping("/category")
    public Message judgeCategory(@RequestParam Integer siteId, @RequestParam Integer categoryId) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(userId);
        if (!user.getUserName().equals("admin")) {
            if (PermissionUtil.permittedCategory(userId, siteId, categoryId, CmsConst.PERMISSION_WRITE)) {
                return super.buildMessage(0, "success", "yes");
            } else {
                return super.buildMessage(2, "no privilege", null);
            }
        } else {
            return super.buildMessage(0, "success", "yes");
        }

    }

    @GetMapping("/site")
    public Message judgeSite(@RequestParam Integer siteId) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(userId);
        if (!user.getUserName().equals("admin")) {
            if (PermissionUtil.permittedSite(userId, siteId)) {
                return super.buildMessage(0, "success", "yes");
            } else {
                return super.buildMessage(2, "no privilege", null);
            }
        } else {
            return super.buildMessage(0, "success", "yes");
        }
    }
}
