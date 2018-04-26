package com.gangster.cms.admin.controller;

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

    @GetMapping("/list/{siteId}")
    public AjaxData list(@PathVariable("siteId") Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<OuterChain> pageInfo = outerChainWebService.list(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/detail/{id}")
    public MessageDto detail(@PathVariable("id") Integer id) {
        return MessageDto.success(outerChainWebService.details(id));
    }


    @PostMapping("/add/{siteId}")
    public MessageDto add(@PathVariable("siteId") Integer siteId, @RequestBody OuterChain outerChain) {

        if (outerChainWebService.add(siteId, outerChain)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "添加失败，请重试");
    }

    @DeleteMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (outerChainWebService.delete(id)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "删除失败请重试");
    }

    @PostMapping("/update/{id}")
    public MessageDto update(@PathVariable("id") Integer id, @RequestBody OuterChain outerChain) {
        if (outerChainWebService.update(id, outerChain)) {
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "更新失败请重试");
    }
}
