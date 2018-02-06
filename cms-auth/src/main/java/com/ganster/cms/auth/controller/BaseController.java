package com.ganster.cms.auth.controller;

import com.ganster.cms.auth.dto.AjaxData;
import com.ganster.cms.auth.dto.Message;

import java.util.ArrayList;
import java.util.List;

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

    protected AjaxData buildAjaxData(int code, String msg, long count, List data) {
        AjaxData ajaxData = new AjaxData();
        ajaxData.setCode(code);
        ajaxData.setMsg(msg);
        ajaxData.setCount(count);
        ajaxData.setData(data);
        return ajaxData;
    }
}
