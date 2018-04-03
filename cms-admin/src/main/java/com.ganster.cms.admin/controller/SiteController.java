package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.SiteExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Create by Yoke on 2018/2/2
 */
@RestController
@RequestMapping("/site")
public class SiteController extends BaseController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public AjaxData list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        SiteExample siteExample = new SiteExample();
        List<Integer> siteIdList = PermissionUtil.getAllPermissionSite(userId);
        if (siteIdList == null || siteIdList.isEmpty()) {
            return super.buildAjaxData(2, "no privilege", 0, null);
        }
        siteExample.or().andSiteIdIn(siteIdList);
        PageInfo<Site> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> siteService.selectByExample(siteExample));
        List<Site> siteList = pageInfo.getList();
        if (siteList == null || siteList.isEmpty()) {
            return super.buildAjaxData(0, "success", 0, null);
        } else {
            return super.buildAjaxData(0, "success", pageInfo.getTotal(), siteList);
        }
    }

    @PostMapping("/add")
    public Message add(@RequestBody Site site) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(userId);
        if (site == null) {
            return super.buildMessage(1, "no data", null);
        }
        site.setSiteCreateTime(new Date());
        site.setSiteStatus(0);
        int count = siteService.insert(site);

        if (count == 0) {
            return super.buildMessage(1, "add site failed", null);
        }
        try {
            if (!user.getUserName().equals("admin")) {
                permissionService.addUserToSite(1, site.getSiteId());
            }
            permissionService.addUserToSite(userId, site.getSiteId());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return super.buildMessage(1, "用户未找到", null);
        }
        PermissionUtil.flush(userId);
        return super.buildMessage(0, "success", count);
    }

    //TODO: some time no flush
    @GetMapping("/delete/{id}")
    public Message delete(@PathVariable("id") Integer id) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        if (id == null) {
            return super.buildMessage(1, "no data", null);
        }
        siteService.deleteSite(id);
        PermissionUtil.flush(userId);
        return super.buildMessage(0, "success", "success");
    }

    @GetMapping("/details/{id}")
    public Site details(@PathVariable("id") Integer id) {
        if (id == null) {
            return null;
        }
        return siteService.selectByPrimaryKey(id);
    }

    @PostMapping("/update/{id}")
    public Message update(@PathVariable("id") Integer id, @RequestBody Site site) {
        if (id == null) {
            return super.buildMessage(0, "no data to update", null);
        }
        site.setSiteId(id);
        int count = siteService.updateByPrimaryKeySelective(site);
        if (count == 0) {
            return super.buildMessage(1, "false to update", null);
        } else {
            return super.buildMessage(0, "success", count);
        }
    }
}
