package com.ganster.cms.web.annotation;

public enum CountType {
    ARTICLE("article"),
    CATEGORY("category"),
    SITE("site");

    private String val;

    CountType(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
