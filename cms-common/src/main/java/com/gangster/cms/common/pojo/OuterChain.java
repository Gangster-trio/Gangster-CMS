package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class OuterChain implements Serializable {
    private Integer outerchainId;

    private String outerchainUrl;

    private Date outerchainCreateTime;

    private String outerchainDesc;

    private Integer outerchainSiteId;

    private String outerchainName;

    private Date outerchainUpdateTime;

    private static final long serialVersionUID = 1L;

    public OuterChain(Integer outerchainId, String outerchainUrl, Date outerchainCreateTime, String outerchainDesc, Integer outerchainSiteId, String outerchainName, Date outerchainUpdateTime) {
        this.outerchainId = outerchainId;
        this.outerchainUrl = outerchainUrl;
        this.outerchainCreateTime = outerchainCreateTime;
        this.outerchainDesc = outerchainDesc;
        this.outerchainSiteId = outerchainSiteId;
        this.outerchainName = outerchainName;
        this.outerchainUpdateTime = outerchainUpdateTime;
    }

    public OuterChain() {
        super();
    }

    public Integer getOuterchainId() {
        return outerchainId;
    }

    public void setOuterchainId(Integer outerchainId) {
        this.outerchainId = outerchainId;
    }

    public String getOuterchainUrl() {
        return outerchainUrl;
    }

    public void setOuterchainUrl(String outerchainUrl) {
        this.outerchainUrl = outerchainUrl == null ? null : outerchainUrl.trim();
    }

    public Date getOuterchainCreateTime() {
        return outerchainCreateTime;
    }

    public void setOuterchainCreateTime(Date outerchainCreateTime) {
        this.outerchainCreateTime = outerchainCreateTime;
    }

    public String getOuterchainDesc() {
        return outerchainDesc;
    }

    public void setOuterchainDesc(String outerchainDesc) {
        this.outerchainDesc = outerchainDesc == null ? null : outerchainDesc.trim();
    }

    public Integer getOuterchainSiteId() {
        return outerchainSiteId;
    }

    public void setOuterchainSiteId(Integer outerchainSiteId) {
        this.outerchainSiteId = outerchainSiteId;
    }

    public String getOuterchainName() {
        return outerchainName;
    }

    public void setOuterchainName(String outerchainName) {
        this.outerchainName = outerchainName == null ? null : outerchainName.trim();
    }

    public Date getOuterchainUpdateTime() {
        return outerchainUpdateTime;
    }

    public void setOuterchainUpdateTime(Date outerchainUpdateTime) {
        this.outerchainUpdateTime = outerchainUpdateTime;
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
        OuterChain other = (OuterChain) that;
        return (this.getOuterchainId() == null ? other.getOuterchainId() == null : this.getOuterchainId().equals(other.getOuterchainId()))
            && (this.getOuterchainUrl() == null ? other.getOuterchainUrl() == null : this.getOuterchainUrl().equals(other.getOuterchainUrl()))
            && (this.getOuterchainCreateTime() == null ? other.getOuterchainCreateTime() == null : this.getOuterchainCreateTime().equals(other.getOuterchainCreateTime()))
            && (this.getOuterchainDesc() == null ? other.getOuterchainDesc() == null : this.getOuterchainDesc().equals(other.getOuterchainDesc()))
            && (this.getOuterchainSiteId() == null ? other.getOuterchainSiteId() == null : this.getOuterchainSiteId().equals(other.getOuterchainSiteId()))
            && (this.getOuterchainName() == null ? other.getOuterchainName() == null : this.getOuterchainName().equals(other.getOuterchainName()))
            && (this.getOuterchainUpdateTime() == null ? other.getOuterchainUpdateTime() == null : this.getOuterchainUpdateTime().equals(other.getOuterchainUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOuterchainId() == null) ? 0 : getOuterchainId().hashCode());
        result = prime * result + ((getOuterchainUrl() == null) ? 0 : getOuterchainUrl().hashCode());
        result = prime * result + ((getOuterchainCreateTime() == null) ? 0 : getOuterchainCreateTime().hashCode());
        result = prime * result + ((getOuterchainDesc() == null) ? 0 : getOuterchainDesc().hashCode());
        result = prime * result + ((getOuterchainSiteId() == null) ? 0 : getOuterchainSiteId().hashCode());
        result = prime * result + ((getOuterchainName() == null) ? 0 : getOuterchainName().hashCode());
        result = prime * result + ((getOuterchainUpdateTime() == null) ? 0 : getOuterchainUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", outerchainId=").append(outerchainId);
        sb.append(", outerchainUrl=").append(outerchainUrl);
        sb.append(", outerchainCreateTime=").append(outerchainCreateTime);
        sb.append(", outerchainDesc=").append(outerchainDesc);
        sb.append(", outerchainSiteId=").append(outerchainSiteId);
        sb.append(", outerchainName=").append(outerchainName);
        sb.append(", outerchainUpdateTime=").append(outerchainUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}