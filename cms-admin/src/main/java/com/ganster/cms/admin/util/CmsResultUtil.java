package com.ganster.cms.admin.util;

import com.ganster.cms.admin.constant.CmsResultConstant;
import com.ganster.cms.admin.dto.MessageDto;

/**
 * @author Yoke
 * Created on 2018/4/15
 */
public class CmsResultUtil<T> {
    private MessageDto<T> message;

    public CmsResultUtil() {
        message = new MessageDto<>();
        message.setCode(CmsResultConstant.SUCCESS.getCode());
        message.setMessage(CmsResultConstant.SUCCESS.getMessage());
    }

    public MessageDto<T> setData(T t) {
        this.message.setData(t);
        return this.message;
    }

    public MessageDto<T> setError(T t) {
        this.message.setCode(CmsResultConstant.FAILED.getCode());
        this.message.setMessage(CmsResultConstant.FAILED.getMessage());
        this.message.setData(t);
        return this.message;
    }
}
