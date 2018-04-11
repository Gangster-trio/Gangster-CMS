package com.ganster.cms.core.pojo;

import java.io.Serializable;

public class CountEntry implements Serializable {
    private Integer countId;

    private String countType;

    private Integer countCid;

    private Integer countPv;

    private String countTime;

    private static final long serialVersionUID = 1L;

    public CountEntry(Integer countId, String countType, Integer countCid, Integer countPv) {
        this.countId = countId;
        this.countType = countType;
        this.countCid = countCid;
        this.countPv = countPv;
    }

    public CountEntry(Integer countId, String countType, Integer countCid, Integer countPv, String countTime) {
        this.countId = countId;
        this.countType = countType;
        this.countCid = countCid;
        this.countPv = countPv;
        this.countTime = countTime;
    }

    public CountEntry() {
        super();
    }

    public Integer getCountId() {
        return countId;
    }

    public void setCountId(Integer countId) {
        this.countId = countId;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType == null ? null : countType.trim();
    }

    public Integer getCountCid() {
        return countCid;
    }

    public void setCountCid(Integer countCid) {
        this.countCid = countCid;
    }

    public Integer getCountPv() {
        return countPv;
    }

    public void setCountPv(Integer countPv) {
        this.countPv = countPv;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime == null ? null : countTime.trim();
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
        CountEntry other = (CountEntry) that;
        return (this.getCountId() == null ? other.getCountId() == null : this.getCountId().equals(other.getCountId()))
            && (this.getCountType() == null ? other.getCountType() == null : this.getCountType().equals(other.getCountType()))
            && (this.getCountCid() == null ? other.getCountCid() == null : this.getCountCid().equals(other.getCountCid()))
            && (this.getCountPv() == null ? other.getCountPv() == null : this.getCountPv().equals(other.getCountPv()))
            && (this.getCountTime() == null ? other.getCountTime() == null : this.getCountTime().equals(other.getCountTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCountId() == null) ? 0 : getCountId().hashCode());
        result = prime * result + ((getCountType() == null) ? 0 : getCountType().hashCode());
        result = prime * result + ((getCountCid() == null) ? 0 : getCountCid().hashCode());
        result = prime * result + ((getCountPv() == null) ? 0 : getCountPv().hashCode());
        result = prime * result + ((getCountTime() == null) ? 0 : getCountTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", countId=").append(countId);
        sb.append(", countType=").append(countType);
        sb.append(", countCid=").append(countCid);
        sb.append(", countPv=").append(countPv);
        sb.append(", countTime=").append(countTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}