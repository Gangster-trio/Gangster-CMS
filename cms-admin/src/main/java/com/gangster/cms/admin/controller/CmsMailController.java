package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.ContentWebService;
import com.gangster.cms.admin.service.web.MailWebService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.CmsMail;
import com.gangster.cms.common.pojo.User;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yoke
 * Created on 2018/4/25
 */
@RestController
@RequestMapping("/mail")
public class CmsMailController {
    @Autowired
    private MailWebService mailWbeService;
    @Autowired
    private ContentWebService contentWebService;

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

    @GetMapping("/list/sended")
    public AjaxData listSended(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<CmsMail> pageInfo = mailWbeService.listSended(user, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @GetMapping("/details/{id}")
    public MessageDto detailsMail(@PathVariable("id") Integer id) {
        return MessageDto.success(mailWbeService.detailsMail(id));
    }

    @PostMapping("/add")
    public MessageDto addMail(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestBody CmsMail cmsMail) {
        if (!mailWbeService.addMail(user, cmsMail)) {
            return MessageDto.fail(1, "添加失败");
        }
        return MessageDto.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (!mailWbeService.deleteMail(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }

    @GetMapping("/mark/{id}")
    public MessageDto mark(@PathVariable("id") Integer id) {
        if (!mailWbeService.markMail(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }

    @PostMapping("/img")
    public MessageDto uploadImg(@Param("file") MultipartFile file) {
        return MessageDto.success(contentWebService.uploadImg(file));
    }

    @PostMapping("/update")
    public MessageDto update(@RequestBody CmsMail cmsMail) {
        if (!mailWbeService.update(cmsMail)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }
}
