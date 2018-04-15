package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Skin implements Serializable {
    private String skinName;

    private Date skinCreateTime;

    private Date skinUpdateTime;

    private static final long serialVersionUID = 1L;

    public Skin(String skinName, Date skinCreateTime, Date skinUpdateTime) {
        this.skinName = skinName;
        this.skinCreateTime = skinCreateTime;
        this.skinUpdateTime = skinUpdateTime;
    }

    public Skin() {
        super();
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName == null ? null : skinName.trim();
    }

    public Date getSkinCreateTime() {
        return skinCreateTime;
    }

    public void setSkinCreateTime(Date skinCreateTime) {
        this.skinCreateTime = skinCreateTime;
    }

    public Date getSkinUpdateTime() {
        return skinUpdateTime;
    }

    public void setSkinUpdateTime(Date skinUpdateTime) {
        this.skinUpdateTime = skinUpdateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Skin other = (Skin) that;
        return (this.getSkinName() == null ? other.getSkinName() == null : this.getSkinName().equals(other.getSkinName()))
            && (this.getSkinCreateTime() == null ? other.getSkinCreateTime() == null : this.getSkinCreateTime().equals(other.getSkinCreateTime()))
            && (this.getSkinUpdateTime() == null ? other.getSkinUpdateTime() == null : this.getSkinUpdateTime().equals(other.getSkinUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSkinName() == null) ? 0 : getSkinName().hashCode());
        result = prime * result + ((getSkinCreateTime() == null) ? 0 : getSkinCreateTime().hashCode());
        result = prime * result + ((getSkinUpdateTime() == null) ? 0 : getSkinUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skinName=").append(skinName);
        sb.append(", skinCreateTime=").append(skinCreateTime);
        sb.append(", skinUpdateTime=").append(skinUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}