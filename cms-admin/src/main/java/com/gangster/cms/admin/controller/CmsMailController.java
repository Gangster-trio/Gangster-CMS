package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.service.web.MailWbeService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.CmsMail;
import com.gangster.cms.common.pojo.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoke
 * Created on 2018/4/25
 */
@RestController
@RequestMapping("/mail")
public class CmsMailController {
    @Autowired
    private MailWbeService mailWbeService;

    @GetMapping("/list/readed")
    public AjaxData listReaded(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<CmsMail> pageInfo = mailWbeService.listReaded(user, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/list/toread")
    public AjaxData listToRead(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<CmsMail> pageInfo = mailWbeService.listToRead(user, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/list/draft")
    public AjaxData listDraft(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<CmsMail> pageInfo = mailWbeService.listDraft(user, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/list/trashcan")
    public AjaxData listTrashCan(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<CmsMail> pageInfo = mailWbeService.trashCan(user, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }
}
