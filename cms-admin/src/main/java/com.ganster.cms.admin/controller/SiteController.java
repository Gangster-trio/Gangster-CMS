package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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


        SiteExample siteExample = new SiteExample();
        PageInfo<Site> pageInfo;
        List<Site> list = siteService.selectByExample(siteExample);
        if (list == null || list.isEmpty()) return super.buildAjaxData(1, "false", 0, null);
        else {
            if (page != null && limit != null) {
                pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> siteService.selectByExample(siteExample));
                return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
            } else {
                pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> siteService.selectByExample(siteExample));
                return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) list);
            }
        }
    }

}
