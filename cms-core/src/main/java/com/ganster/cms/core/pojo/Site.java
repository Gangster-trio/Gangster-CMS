package com.ganster.cms.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class Site implements Serializable {
    private Integer siteId;

    private String siteName;

    private String siteUrl;

    private String siteDesc;

    private String siteCopyright;

    private String siteSkin;

    private Date siteCreateTime;

    private Integer siteStatus;

    private String sitePic;

    private static final long serialVersionUID = 1L;

    public Site(Integer siteId, String siteName, String siteUrl, String siteDesc, String siteCopyright, String siteSkin, Date siteCreateTime, Integer siteStatus, String sitePic) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.siteDesc = siteDesc;
        this.siteCopyright = siteCopyright;
        this.siteSkin = siteSkin;
        this.siteCreateTime = siteCreateTime;
        this.siteStatus = siteStatus;
        this.sitePic = sitePic;
    }

    public Site() {
        super();
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc == null ? null : siteDesc.trim();
    }

    public String getSiteCopyright() {
        return siteCopyright;
    }

    public void setSiteCopyright(String siteCopyright) {
        this.siteCopyright = siteCopyright == null ? null : siteCopyright.trim();
    }

    public String getSiteSkin() {
        return siteSkin;
    }

    public void setSiteSkin(String siteSkin) {
        this.siteSkin = siteSkin == null ? null : siteSkin.trim();
    }

    public Date getSiteCreateTime() {
        return siteCreateTime;
    }

    public void setSiteCreateTime(Date siteCreateTime) {
        this.siteCreateTime = siteCreateTime;
    }

    public Integer getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(Integer siteStatus) {
        this.siteStatus = siteStatus;
    }

    public String getSitePic() {
        return sitePic;
    }

    public void setSitePic(String sitePic) {
        this.sitePic = sitePic == null ? null : sitePic.trim();
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
        Site other = (Site) that;
        return (this.getSiteId() == null ? other.getSiteId() == null : this.getSiteId().equals(other.getSiteId()))
            && (this.getSiteName() == null ? other.getSiteName() == null : this.getSiteName().equals(other.getSiteName()))
            && (this.getSiteUrl() == null ? other.getSiteUrl() == null : this.getSiteUrl().equals(other.getSiteUrl()))
            && (this.getSiteDesc() == null ? other.getSiteDesc() == null : this.getSiteDesc().equals(other.getSiteDesc()))
            && (this.getSiteCopyright() == null ? other.getSiteCopyright() == null : this.getSiteCopyright().equals(other.getSiteCopyright()))
            && (this.getSiteSkin() == null ? other.getSiteSkin() == null : this.getSiteSkin().equals(other.getSiteSkin()))
            && (this.getSiteCreateTime() == null ? other.getSiteCreateTime() == null : this.getSiteCreateTime().equals(other.getSiteCreateTime()))
            && (this.getSiteStatus() == null ? other.getSiteStatus() == null : this.getSiteStatus().equals(other.getSiteStatus()))
            && (this.getSitePic() == null ? other.getSitePic() == null : this.getSitePic().equals(other.getSitePic()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSiteId() == null) ? 0 : getSiteId().hashCode());
        result = prime * result + ((getSiteName() == null) ? 0 : getSiteName().hashCode());
        result = prime * result + ((getSiteUrl() == null) ? 0 : getSiteUrl().hashCode());
        result = prime * result + ((getSiteDesc() == null) ? 0 : getSiteDesc().hashCode());
        result = prime * result + ((getSiteCopyright() == null) ? 0 : getSiteCopyright().hashCode());
        result = prime * result + ((getSiteSkin() == null) ? 0 : getSiteSkin().hashCode());
        result = prime * result + ((getSiteCreateTime() == null) ? 0 : getSiteCreateTime().hashCode());
        result = prime * result + ((getSiteStatus() == null) ? 0 : getSiteStatus().hashCode());
        result = prime * result + ((getSitePic() == null) ? 0 : getSitePic().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", siteId=").append(siteId);
        sb.append(", siteName=").append(siteName);
        sb.append(", siteUrl=").append(siteUrl);
        sb.append(", siteDesc=").append(siteDesc);
        sb.append(", siteCopyright=").append(siteCopyright);
        sb.append(", siteSkin=").append(siteSkin);
        sb.append(", siteCreateTime=").append(siteCreateTime);
        sb.append(", siteStatus=").append(siteStatus);
        sb.append(", sitePic=").append(sitePic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}