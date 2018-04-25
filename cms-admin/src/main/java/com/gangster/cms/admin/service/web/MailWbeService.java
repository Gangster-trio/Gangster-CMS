package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.CmsMailService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.CmsMail;
import com.gangster.cms.common.pojo.CmsMailExample;
import com.gangster.cms.common.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/25
 */
@Service
public class MailWbeService {
    @Autowired
    private CmsMailService cmsMailService;

    /**
     * 列出当前用户已经读取的邮件
     */
    public PageInfo<CmsMail> listReaded(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出已经发送的，收件是当前用户，未读取的邮件
        cmsMailExample.or().andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED).andMailToMailEqualTo(user.getUserEmail()).andMailReadEqualTo(CmsConst.MAIIL_READ_READED);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> cmsMailService.selectByExample(cmsMailExample));
    }

    /**
     * 列出当前用户未读取的邮件
     */
    public PageInfo<CmsMail> listToRead(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        cmsMailExample.or().andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED).andMailToMailEqualTo(user.getUserEmail()).andMailReadEqualTo(CmsConst.MAIIL_READ_TOREAD);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> cmsMailService.selectByExample(cmsMailExample));
    }

    /**
     * 列出当前用户存的草稿
     */
    public PageInfo<CmsMail> listDraft(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出是草稿，发送者是当前用户
        cmsMailExample.or().andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_DRAFT).andMailInMailEqualTo(user.getUserEmail());
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> cmsMailService.selectByExample(cmsMailExample));
    }

    /**
     * 列出当前用户的回收站
     */
    public PageInfo<CmsMail> trashCan(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出收件人是当前用户，邮件的状态是已经发送，且已经删除的状态
        cmsMailExample.or().andMailToMailEqualTo(user.getUserEmail()).andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED).andMailReadEqualTo(CmsConst.MAIL_READ_DELETED);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> cmsMailService.selectByExample(cmsMailExample));
    }

}
