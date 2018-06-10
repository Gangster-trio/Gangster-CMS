package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.ContentWebService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.common.pojo.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by Yoke on 2018/2/2
 */
@RestController
@RequestMapping("/site")
public class SiteController {


    private final ContentWebService contentWebService;

    @Autowired
    public SiteController(ContentWebService contentWebService) {
        this.contentWebService = contentWebService;
    }

    @SystemControllerLog(description = "列出所有的站")
    @GetMapping("/list")
    public AjaxData list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Site> pageInfo = contentWebService.listSite(page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "添加站")
    @PostMapping("/add")
    public MessageDto add(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestBody Site site) {
        if (!contentWebService.addSite(user, site)) {
            return MessageDto.fail(1, "添加站失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "删除站")
    @GetMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (!contentWebService.deleteSite(id)) {
            return MessageDto.fail(1, "删除站失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "查看站的信息")
    @GetMapping("/details/{id}")
    public Site details(@PathVariable("id") Integer id) {
        return contentWebService.detailsSite(id);
    }

    @SystemControllerLog(description = "更新单个站")
    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, @RequestBody Site site) {
        if (!contentWebService.updateSite(id, site)) {
            return MessageDto.fail(1, "更新单个站失败");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "批量删除")
    public MessageDto batchDelete(String siteIds) {
        if (!contentWebService.deleteSites(siteIds)) {
            return MessageDto.fail(1, "批量删除站点发生错误");
        }
        return MessageDto.success(null);
    }
}
