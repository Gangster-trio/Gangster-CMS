package com.ganster.cms.core.dao.pojo;

import java.util.Date;

public class Tag {
    private Integer tagId;

    private String tagName;

    private Date tagCreateTime;

    public Tag(String tagName, Date tagCreateTime) {
        this.tagName = tagName;
        this.tagCreateTime = tagCreateTime;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public Date getTagCreateTime() {
        return tagCreateTime;
    }

    public void setTagCreateTime(Date tagCreateTime) {
        this.tagCreateTime = tagCreateTime;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagCreateTime=" + tagCreateTime +
                '}';
    }
}