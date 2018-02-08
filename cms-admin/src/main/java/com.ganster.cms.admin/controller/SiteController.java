package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.SiteExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private UserService userService;

    @GetMapping("/list")
    public AjaxData list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        SiteExample siteExample = new SiteExample();
        PageInfo<Site> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> siteService.selectByExample(siteExample));
        List<Site> list = pageInfo.getList();
        if (list == null || list.isEmpty()) {
            return super.buildAjaxData(1, "no data", 0, null);
        } else {
            return super.buildAjaxData(0, "success", pageInfo.getTotal(), list);
        }
    }

    @PostMapping("/add")
    public Message add(@RequestBody Site site) {
        if (site == null) {
            return super.buildMessage(1, "no data", null);
        }

        site.setSiteCreateTime(new Date());
        int count = siteService.insert(site);
        if (count == 0) {
            return super.buildMessage(1, "add site failed", null);
        }
        return super.buildMessage(0, "success", count);
    }

    @GetMapping("/delete")
    public Message delete(@PathVariable Integer id) {
        if (id == null) {
            return super.buildMessage(1, "no data", null);
        }
        return null;
    }

}
