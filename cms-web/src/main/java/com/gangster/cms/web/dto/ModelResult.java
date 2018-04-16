package com.gangster.cms.web.dto;

import java.util.HashMap;
import java.util.Map;

public class ModelResult {
    private Map<String, Object> map = new HashMap<>();

    private String msg;

    /**
     * 0 - success,
     * other - fail
     */
    private Integer code;

    public static ModelResult success() {
        ModelResult modelResult = new ModelResult();
        modelResult.code = 0;
        modelResult.msg = "success";
        return modelResult;
    }

    public static ModelResult fail(Integer code, String msg) {
        ModelResult modelResult = new ModelResult();
        modelResult.code = code;
        modelResult.msg = msg;
        return modelResult;
    }

    public ModelResult put(String key, Object value) {
        map.put(key, value);
        return this;
    }

    public Object get(String key) {
        return map.get(key);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
