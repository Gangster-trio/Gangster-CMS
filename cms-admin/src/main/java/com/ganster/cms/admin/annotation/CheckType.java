package com.ganster.cms.admin.annotation;

/**
 * @author Yoke
 * Created on 2018/4/14
 */
public enum CheckType {
    ARTICLE_READ("article:read"),
    ARTICLE_WRITE("article:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category_write"),
    SITE_READ("site:read"),
    SITE_wRITE("site:write");


    private String val;

    CheckType(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
