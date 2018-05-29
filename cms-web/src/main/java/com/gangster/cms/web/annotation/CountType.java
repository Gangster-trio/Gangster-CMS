package com.gangster.cms.web.annotation;

public enum CountType {
    ARTICLE("article"),
    CATEGORY("category"),
    SITE("site"),
    SURVEY("survey");

    private final String val;

    CountType(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
