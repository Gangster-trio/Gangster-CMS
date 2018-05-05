package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class SurveyPage implements Serializable {
    private Integer pageId;

    private String pageTitle;

    private Date pageCreateTime;

    private Date pageEndTime;

    private Integer pageSiteId;

    private static final long serialVersionUID = 1L;

    public SurveyPage(Integer pageId, String pageTitle, Date pageCreateTime, Date pageEndTime, Integer pageSiteId) {
        this.pageId = pageId;
        this.pageTitle = pageTitle;
        this.pageCreateTime = pageCreateTime;
        this.pageEndTime = pageEndTime;
        this.pageSiteId = pageSiteId;
    }

    public SurveyPage(String pageTitle, Date pageCreateTime, Date pageEndTime, Integer pageSiteId) {
        this.pageTitle = pageTitle;
        this.pageCreateTime = pageCreateTime;
        this.pageEndTime = pageEndTime;
        this.pageSiteId = pageSiteId;
    }

    public SurveyPage() {
        super();
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle == null ? null : pageTitle.trim();
    }

    public Date getPageCreateTime() {
        return pageCreateTime;
    }

    public void setPageCreateTime(Date pageCreateTime) {
        this.pageCreateTime = pageCreateTime;
    }

    public Date getPageEndTime() {
        return pageEndTime;
    }

    public void setPageEndTime(Date pageEndTime) {
        this.pageEndTime = pageEndTime;
    }

    public Integer getPageSiteId() {
        return pageSiteId;
    }

    public void setPageSiteId(Integer pageSiteId) {
        this.pageSiteId = pageSiteId;
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
        SurveyPage other = (SurveyPage) that;
        return (this.getPageId() == null ? other.getPageId() == null : this.getPageId().equals(other.getPageId()))
            && (this.getPageTitle() == null ? other.getPageTitle() == null : this.getPageTitle().equals(other.getPageTitle()))
            && (this.getPageCreateTime() == null ? other.getPageCreateTime() == null : this.getPageCreateTime().equals(other.getPageCreateTime()))
            && (this.getPageEndTime() == null ? other.getPageEndTime() == null : this.getPageEndTime().equals(other.getPageEndTime()))
            && (this.getPageSiteId() == null ? other.getPageSiteId() == null : this.getPageSiteId().equals(other.getPageSiteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPageId() == null) ? 0 : getPageId().hashCode());
        result = prime * result + ((getPageTitle() == null) ? 0 : getPageTitle().hashCode());
        result = prime * result + ((getPageCreateTime() == null) ? 0 : getPageCreateTime().hashCode());
        result = prime * result + ((getPageEndTime() == null) ? 0 : getPageEndTime().hashCode());
        result = prime * result + ((getPageSiteId() == null) ? 0 : getPageSiteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pageId=").append(pageId);
        sb.append(", pageTitle=").append(pageTitle);
        sb.append(", pageCreateTime=").append(pageCreateTime);
        sb.append(", pageEndTime=").append(pageEndTime);
        sb.append(", pageSiteId=").append(pageSiteId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}