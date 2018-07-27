package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.OuterChainWebService;
import com.gangster.cms.common.pojo.OuterChain;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoke
 * Created on 2018/4/23
 */
@RestController
@RequestMapping("outerchain")
public class OuterChainController {

    @Autowired
    private OuterChainWebService outerChainWebService;

    @GetMapping("/{siteId}")
    @CmsPermission(moduleName = "外链管理")
    public AjaxData list(
            @SiteId @PathVariable("siteId") Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<OuterChain> pageInfo = outerChainWebService.list(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/detail/{siteId}/{id}")
    @CmsPermission(moduleName = "外链管理")
    public MessageDto detail(@SiteId @PathVariable Integer siteId, @PathVariable("id") Integer id) {
        return MessageDto.success(outerChainWebService.details(id));
    }


    @PostMapping("/{siteId}")
    @CmsPermission(moduleName = "外链管理")
    public MessageDto add(@SiteId @PathVariable Integer siteId, @RequestBody OuterChain outerChain) {

        if (outerChainWebService.add(siteId, outerChain)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "添加失败，请重试");
    }

    @DeleteMapping("/{siteId}/{id}")
    @CmsPermission(moduleName = "外链管理")
    public MessageDto delete(@PathVariable Integer siteId, @PathVariable("id") Integer id) {
        if (outerChainWebService.delete(id)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "删除失败请重试");
    }

    @PutMapping("/update/{siteId}/{id}")
    @CmsPermission(moduleName = "外链管理")
    public MessageDto update(
            @PathVariable Integer siteId,
            @PathVariable("id") Integer id,
            @RequestBody OuterChain outerChain) {
        if (outerChainWebService.update(id, outerChain)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "更新失败请重试");
    }
}
