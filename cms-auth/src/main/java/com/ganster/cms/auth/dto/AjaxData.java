package com.ganster.cms.auth.dto;

import java.util.ArrayList;

/**
 * layui要求返回的数据格式
 */
public class AjaxData {
    private int code = 0;
    private String msg = "";
    private long count;
    private ArrayList data;

    public AjaxData(int code, String msg, long count, ArrayList data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public AjaxData() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public ArrayList getData() {
        return data;
    }

    public void setData(ArrayList data) {
        this.data = data;
    }
}
