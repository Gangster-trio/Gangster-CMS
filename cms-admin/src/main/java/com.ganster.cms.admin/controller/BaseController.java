package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.dto.Message;

import java.util.ArrayList;

/**
 * Create by Yoke on 2018/1/30
 */

public class BaseController {

    protected Message buildMessage(int code, String msg, Object data) {
        Message message = new Message();
        message.setCode(code);
        message.setMsg(msg);
        message.setData(data);
        return message;
    }

    protected AjaxData buildAjaxData(int code, String msg, long count, ArrayList data) {
        AjaxData ajaxData = new AjaxData();
        ajaxData.setCode(code);
        ajaxData.setMsg(msg);
        ajaxData.setCount(count);
        ajaxData.setData(data);
        return ajaxData;
    }
}
