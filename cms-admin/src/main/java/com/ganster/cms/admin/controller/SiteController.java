package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.admin.service.ContentWebService;
import com.ganster.cms.admin.util.CmsResultUtil;
import com.ganster.cms.core.pojo.Site;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create by Yoke on 2018/2/2
 */
@RestController
@RequestMapping("/site")
public class SiteController {


    @Autowired
    private ContentWebService contentWebService;

    @SystemControllerLog(description = "列出所有的站")
    @GetMapping("/list")
    public AjaxData list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Site> pageInfo = contentWebService.listSite(page, limit);
        if (pageInfo == null) {
            return new AjaxData(1, "false", 0, null);
        }
        List<Site> siteList = pageInfo.getList();
        return new AjaxData(0, "success", pageInfo.getTotal(), siteList);
    }

    @SystemControllerLog(description = "添加站")
    @PostMapping("/add")
    public MessageDto add(@RequestBody Site site) {
        if (contentWebService.addSite(site)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError("添加失败");
    }

    @SystemControllerLog(description = "删除站")
    @GetMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (contentWebService.deleteSite(id)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError("删除站点失败");
    }

    @SystemControllerLog(description = "查看站的信息")
    @GetMapping("/details/{id}")
    public Site details(@PathVariable("id") Integer id) {
        return contentWebService.detailsSite(id);
    }

    @SystemControllerLog(description = "更新单个站")
    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, @RequestBody Site site) {
        if (contentWebService.updateSite(id, site)) {
            return new CmsResultUtil<>().setData(null);
        }
        return new CmsResultUtil<>().setError("更新站点失败");
    }
}
