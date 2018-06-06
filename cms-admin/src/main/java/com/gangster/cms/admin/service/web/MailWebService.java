package com.gangster.cms.admin.service.web;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.CmsMail;
import com.gangster.cms.common.pojo.CmsMailExample;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.dao.mapper.CmsMailMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/25
 */
@Service
public class MailWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailWebService.class);

    private final CmsMailMapper cmsMailMapper;

    @Autowired
    public MailWebService(CmsMailMapper cmsMailMapper) {
        this.cmsMailMapper = cmsMailMapper;
    }

    /**
     * 列出当前用户已经读取的邮件
     */
    public PageInfo<CmsMail> listReaded(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出已经发送的，收件是当前用户，未读取的邮件
        cmsMailExample.or()
                .andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED)
                .andMailToMailEqualTo(user.getUserEmail())
                .andMailReadEqualTo(CmsConst.MAIIL_READ_READED);
        return PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> cmsMailMapper.selectByExample(cmsMailExample));
    }

    /**
     * 列出当前用户未读取的邮件
     */
    public PageInfo<CmsMail> listToRead(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        cmsMailExample.or()
                .andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED)
                .andMailToMailEqualTo(user.getUserEmail())
                .andMailReadEqualTo(CmsConst.MAIIL_READ_TOREAD);
        return PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> cmsMailMapper.selectByExample(cmsMailExample));
    }

    /**
     * 列出当前用户存的草稿
     */
    public PageInfo<CmsMail> listDraft(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出是草稿,且未删除,发送者是当前用户
        cmsMailExample.or()
                .andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_DRAFT)
                .andMailReadNotEqualTo(CmsConst.MAIL_READ_DELETED)
                .andMailInMailEqualTo(user.getUserEmail());
        return PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> cmsMailMapper.selectByExample(cmsMailExample));
    }

    /**
     * 列出当前用户已发送的邮件
     */
    public PageInfo<CmsMail> listSended(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出邮件状态是已发送,发送方为user
        cmsMailExample.or()
                .andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED)
                .andMailInMailEqualTo(user.getUserEmail());
        return PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> cmsMailMapper.selectByExample(cmsMailExample));
    }


    /**
     * 列出当前用户的回收站
     */
    public PageInfo<CmsMail> trashCan(User user, Integer page, Integer limit) {
        CmsMailExample cmsMailExample = new CmsMailExample();
        // 列出收件人是当前用户，邮件的状态是已经发送，且已经删除的状态
        cmsMailExample.or()
                .andMailToMailEqualTo(user.getUserEmail())
                .andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED)
                .andMailReadEqualTo(CmsConst.MAIL_READ_DELETED);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> cmsMailMapper.selectByExample(cmsMailExample));
    }

    public CmsMail detailsMail(Integer id) {
        return cmsMailMapper.selectByPrimaryKey(id);
    }

    public boolean addMail(User user, CmsMail cmsMail) {
        try {
            if (cmsMail.getMailInMail() == null) {
                cmsMail.setMailInMail(user.getUserEmail());
            }
            cmsMailMapper.insert(cmsMail);
        } catch (Exception e) {
            LOGGER.error("添加邮件失败:{}",e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteMail(Integer id) {
        CmsMail mail = cmsMailMapper.selectByPrimaryKey(id);
        if (mail != null) {
            mail.setMailRead(CmsConst.MAIL_READ_DELETED);
            cmsMailMapper.updateByPrimaryKey(mail);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新标记为已读
     */
    public boolean markMail(Integer id) {
        CmsMail mail = cmsMailMapper.selectByPrimaryKey(id);
        if (mail != null) {
            mail.setMailRead(CmsConst.MAIIL_READ_READED);
            cmsMailMapper.updateByPrimaryKey(mail);
            return true;
        } else {
            return false;
        }
    }


    public boolean update(CmsMail cmsMail) {
        return cmsMailMapper.updateByPrimaryKey(cmsMail) == 1;
    }

}
