package com.ganster.cms.admin.dto;

/**
 * @author Yoke
 * Created on 2018/4/15
 */
public class MessageDto<T> {
    private int code;
    private String message;
    private T data;

    @Override
    public String toString() {
        return "MessageDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public MessageDto() {
    }

    public MessageDto(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
