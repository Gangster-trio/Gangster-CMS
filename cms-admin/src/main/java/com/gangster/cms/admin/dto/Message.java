package com.gangster.cms.admin.dto;

/**
 * 统一消息传送包
 *
 * @author bigmeng
 * @author jiangjiang
 * @author smy
 * @version 1.0
 */
public class Message {

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 解释信息
     */
    private String msg;

    /**
     * 需要传送的数据
     */
    private Object data;

    public Message(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Message(int code) {
        this.code = code;
    }

    public Message() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
