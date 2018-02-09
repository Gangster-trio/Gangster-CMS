package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.SiteExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.PermissionUtil;
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


    @GetMapping("/list")
    public AjaxData list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        SiteExample siteExample = new SiteExample();
        List<Site> list = siteService.selectByExample(siteExample);
        List<Site> siteList = new ArrayList<>();
        for (Site site : list) {
            if (PermissionUtil.permittedSite(userId, site.getSiteId())) {
                siteList.add(site);
            }
        }
        return super.buildAjaxData(0, "success", siteList.size(), siteList);
    }

    @PostMapping("/add")
    public Message add(@RequestBody Site site) {
        if (site == null) {
            return super.buildMessage(1, "no data", null);
        }

        site.setSiteCreateTime(new Date());
        site.setSiteStatus(0);
        int count = siteService.insert(site);
        if (count == 0) {
            return super.buildMessage(1, "add site failed", null);
        }
        return super.buildMessage(0, "success", count);
    }

    @GetMapping("/delete/{id}")
    public Message delete(@PathVariable("id") Integer id) {
        if (id == null) {
            return super.buildMessage(1, "no data", null);
        }
        int count = siteService.deleteByPrimaryKey(id);
        if (count == 0) {
            return super.buildMessage(1, "false", null);
        } else {
            return super.buildMessage(0, "success", count);
        }
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
