package com.gangster.cms.admin.dto;

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

    public static MessageDto<Object> success(Object data) {
        return new MessageDto<>(0, "success", data);
    }

    public static MessageDto<Object> fail(int code, String msg) {
        return new MessageDto<>(code, msg, null);
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
