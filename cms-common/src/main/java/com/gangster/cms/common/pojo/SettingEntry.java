package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class SettingEntry implements Serializable {
    private String sysKey;

    private String sysValue;

    private Date sysCreateTime;

    private Date sysUpdateTime;

    private static final long serialVersionUID = 1L;

    public SettingEntry(String sysKey, String sysValue, Date sysCreateTime, Date sysUpdateTime) {
        this.sysKey = sysKey;
        this.sysValue = sysValue;
        this.sysCreateTime = sysCreateTime;
        this.sysUpdateTime = sysUpdateTime;
    }

    public SettingEntry() {
        super();
    }

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey == null ? null : sysKey.trim();
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue == null ? null : sysValue.trim();
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public Date getSysUpdateTime() {
        return sysUpdateTime;
    }

    public void setSysUpdateTime(Date sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
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
        SettingEntry other = (SettingEntry) that;
        return (this.getSysKey() == null ? other.getSysKey() == null : this.getSysKey().equals(other.getSysKey()))
            && (this.getSysValue() == null ? other.getSysValue() == null : this.getSysValue().equals(other.getSysValue()))
            && (this.getSysCreateTime() == null ? other.getSysCreateTime() == null : this.getSysCreateTime().equals(other.getSysCreateTime()))
            && (this.getSysUpdateTime() == null ? other.getSysUpdateTime() == null : this.getSysUpdateTime().equals(other.getSysUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSysKey() == null) ? 0 : getSysKey().hashCode());
        result = prime * result + ((getSysValue() == null) ? 0 : getSysValue().hashCode());
        result = prime * result + ((getSysCreateTime() == null) ? 0 : getSysCreateTime().hashCode());
        result = prime * result + ((getSysUpdateTime() == null) ? 0 : getSysUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sysKey=").append(sysKey);
        sb.append(", sysValue=").append(sysValue);
        sb.append(", sysCreateTime=").append(sysCreateTime);
        sb.append(", sysUpdateTime=").append(sysUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}