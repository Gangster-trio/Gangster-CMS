package com.ganster.cms.core.dao.pojo;

import java.util.Date;

public class Site {
    private Integer siteId;

    private String siteName;

    private String siteUrl;

    private String siteDesc;

    private String siteCopyright;

    private Integer siteSkin;

    private Date siteCreateTime;

    private Integer siteStatus;

    private String sitePic;

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

    public Integer getSiteSkin() {
        return siteSkin;
    }

    public void setSiteSkin(Integer siteSkin) {
        this.siteSkin = siteSkin;
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
    public String toString() {
        return "Site{" +
                "siteId=" + siteId +
                ", siteName='" + siteName + '\'' +
                ", siteUrl='" + siteUrl + '\'' +
                ", siteDesc='" + siteDesc + '\'' +
                ", siteCopyright='" + siteCopyright + '\'' +
                ", siteSkin=" + siteSkin +
                ", siteCreateTime=" + siteCreateTime +
                ", siteStatus=" + siteStatus +
                ", sitePic='" + sitePic + '\'' +
                '}';
    }
}