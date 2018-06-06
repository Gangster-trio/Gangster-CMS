package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Integer articleId;

    private String articleTitle;

    private String articleType;

    private String articleAuthor;

    private String articleUrl;

    private Integer articleOrder;

    private Integer articleSiteId;

    private Integer articleCategoryId;

    private Date articleCreateTime;

    private Date articleUpdateTime;

    private String articleThumb;

    private Integer articleHit;

    private String articleDesc;

    private Integer articleStatus;

    private String articleSkin;

    private Boolean articleInHomepage;

    private Date articleReleaseTime;

    private Boolean articleReleaseStatus;

    private String articleContent;

    private static final long serialVersionUID = 1L;

    public Article(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, Boolean articleInHomepage, Date articleReleaseTime, Boolean articleReleaseStatus) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleType = articleType;
        this.articleAuthor = articleAuthor;
        this.articleUrl = articleUrl;
        this.articleOrder = articleOrder;
        this.articleSiteId = articleSiteId;
        this.articleCategoryId = articleCategoryId;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
        this.articleThumb = articleThumb;
        this.articleHit = articleHit;
        this.articleDesc = articleDesc;
        this.articleStatus = articleStatus;
        this.articleSkin = articleSkin;
        this.articleInHomepage = articleInHomepage;
        this.articleReleaseTime = articleReleaseTime;
        this.articleReleaseStatus = articleReleaseStatus;
    }

    public Article(String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, Boolean articleInHomepage, Date articleReleaseTime, Boolean articleReleaseStatus, String articleContent) {
        this.articleTitle = articleTitle;
        this.articleType = articleType;
        this.articleAuthor = articleAuthor;
        this.articleUrl = articleUrl;
        this.articleOrder = articleOrder;
        this.articleSiteId = articleSiteId;
        this.articleCategoryId = articleCategoryId;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
        this.articleThumb = articleThumb;
        this.articleHit = articleHit;
        this.articleDesc = articleDesc;
        this.articleStatus = articleStatus;
        this.articleSkin = articleSkin;
        this.articleInHomepage = articleInHomepage;
        this.articleReleaseTime = articleReleaseTime;
        this.articleReleaseStatus = articleReleaseStatus;
        this.articleContent = articleContent;
    }

    public Article(Integer articleId, String articleTitle, String articleType, String articleAuthor, String articleUrl, Integer articleOrder, Integer articleSiteId, Integer articleCategoryId, Date articleCreateTime, Date articleUpdateTime, String articleThumb, Integer articleHit, String articleDesc, Integer articleStatus, String articleSkin, Boolean articleInHomepage, Date articleReleaseTime, Boolean articleReleaseStatus, String articleContent) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleType = articleType;
        this.articleAuthor = articleAuthor;
        this.articleUrl = articleUrl;
        this.articleOrder = articleOrder;
        this.articleSiteId = articleSiteId;
        this.articleCategoryId = articleCategoryId;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
        this.articleThumb = articleThumb;
        this.articleHit = articleHit;
        this.articleDesc = articleDesc;
        this.articleStatus = articleStatus;
        this.articleSkin = articleSkin;
        this.articleInHomepage = articleInHomepage;
        this.articleReleaseTime = articleReleaseTime;
        this.articleReleaseStatus = articleReleaseStatus;
        this.articleContent = articleContent;
    }

    public Article() {
        super();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType == null ? null : articleType.trim();
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor == null ? null : articleAuthor.trim();
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    public Integer getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(Integer articleOrder) {
        this.articleOrder = articleOrder;
    }

    public Integer getArticleSiteId() {
        return articleSiteId;
    }

    public void setArticleSiteId(Integer articleSiteId) {
        this.articleSiteId = articleSiteId;
    }

    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public String getArticleThumb() {
        return articleThumb;
    }

    public void setArticleThumb(String articleThumb) {
        this.articleThumb = articleThumb == null ? null : articleThumb.trim();
    }

    public Integer getArticleHit() {
        return articleHit;
    }

    public void setArticleHit(Integer articleHit) {
        this.articleHit = articleHit;
    }

    public String getArticleDesc() {
        return articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc == null ? null : articleDesc.trim();
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleSkin() {
        return articleSkin;
    }

    public void setArticleSkin(String articleSkin) {
        this.articleSkin = articleSkin == null ? null : articleSkin.trim();
    }

    public Boolean getArticleInHomepage() {
        return articleInHomepage;
    }

    public void setArticleInHomepage(Boolean articleInHomepage) {
        this.articleInHomepage = articleInHomepage;
    }

    public Date getArticleReleaseTime() {
        return articleReleaseTime;
    }

    public void setArticleReleaseTime(Date articleReleaseTime) {
        this.articleReleaseTime = articleReleaseTime;
    }

    public Boolean getArticleReleaseStatus() {
        return articleReleaseStatus;
    }

    public void setArticleReleaseStatus(Boolean articleReleaseStatus) {
        this.articleReleaseStatus = articleReleaseStatus;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
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
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getArticleTitle() == null ? other.getArticleTitle() == null : this.getArticleTitle().equals(other.getArticleTitle()))
            && (this.getArticleType() == null ? other.getArticleType() == null : this.getArticleType().equals(other.getArticleType()))
            && (this.getArticleAuthor() == null ? other.getArticleAuthor() == null : this.getArticleAuthor().equals(other.getArticleAuthor()))
            && (this.getArticleUrl() == null ? other.getArticleUrl() == null : this.getArticleUrl().equals(other.getArticleUrl()))
            && (this.getArticleOrder() == null ? other.getArticleOrder() == null : this.getArticleOrder().equals(other.getArticleOrder()))
            && (this.getArticleSiteId() == null ? other.getArticleSiteId() == null : this.getArticleSiteId().equals(other.getArticleSiteId()))
            && (this.getArticleCategoryId() == null ? other.getArticleCategoryId() == null : this.getArticleCategoryId().equals(other.getArticleCategoryId()))
            && (this.getArticleCreateTime() == null ? other.getArticleCreateTime() == null : this.getArticleCreateTime().equals(other.getArticleCreateTime()))
            && (this.getArticleUpdateTime() == null ? other.getArticleUpdateTime() == null : this.getArticleUpdateTime().equals(other.getArticleUpdateTime()))
            && (this.getArticleThumb() == null ? other.getArticleThumb() == null : this.getArticleThumb().equals(other.getArticleThumb()))
            && (this.getArticleHit() == null ? other.getArticleHit() == null : this.getArticleHit().equals(other.getArticleHit()))
            && (this.getArticleDesc() == null ? other.getArticleDesc() == null : this.getArticleDesc().equals(other.getArticleDesc()))
            && (this.getArticleStatus() == null ? other.getArticleStatus() == null : this.getArticleStatus().equals(other.getArticleStatus()))
            && (this.getArticleSkin() == null ? other.getArticleSkin() == null : this.getArticleSkin().equals(other.getArticleSkin()))
            && (this.getArticleInHomepage() == null ? other.getArticleInHomepage() == null : this.getArticleInHomepage().equals(other.getArticleInHomepage()))
            && (this.getArticleReleaseTime() == null ? other.getArticleReleaseTime() == null : this.getArticleReleaseTime().equals(other.getArticleReleaseTime()))
            && (this.getArticleReleaseStatus() == null ? other.getArticleReleaseStatus() == null : this.getArticleReleaseStatus().equals(other.getArticleReleaseStatus()))
            && (this.getArticleContent() == null ? other.getArticleContent() == null : this.getArticleContent().equals(other.getArticleContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getArticleTitle() == null) ? 0 : getArticleTitle().hashCode());
        result = prime * result + ((getArticleType() == null) ? 0 : getArticleType().hashCode());
        result = prime * result + ((getArticleAuthor() == null) ? 0 : getArticleAuthor().hashCode());
        result = prime * result + ((getArticleUrl() == null) ? 0 : getArticleUrl().hashCode());
        result = prime * result + ((getArticleOrder() == null) ? 0 : getArticleOrder().hashCode());
        result = prime * result + ((getArticleSiteId() == null) ? 0 : getArticleSiteId().hashCode());
        result = prime * result + ((getArticleCategoryId() == null) ? 0 : getArticleCategoryId().hashCode());
        result = prime * result + ((getArticleCreateTime() == null) ? 0 : getArticleCreateTime().hashCode());
        result = prime * result + ((getArticleUpdateTime() == null) ? 0 : getArticleUpdateTime().hashCode());
        result = prime * result + ((getArticleThumb() == null) ? 0 : getArticleThumb().hashCode());
        result = prime * result + ((getArticleHit() == null) ? 0 : getArticleHit().hashCode());
        result = prime * result + ((getArticleDesc() == null) ? 0 : getArticleDesc().hashCode());
        result = prime * result + ((getArticleStatus() == null) ? 0 : getArticleStatus().hashCode());
        result = prime * result + ((getArticleSkin() == null) ? 0 : getArticleSkin().hashCode());
        result = prime * result + ((getArticleInHomepage() == null) ? 0 : getArticleInHomepage().hashCode());
        result = prime * result + ((getArticleReleaseTime() == null) ? 0 : getArticleReleaseTime().hashCode());
        result = prime * result + ((getArticleReleaseStatus() == null) ? 0 : getArticleReleaseStatus().hashCode());
        result = prime * result + ((getArticleContent() == null) ? 0 : getArticleContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", articleTitle=").append(articleTitle);
        sb.append(", articleType=").append(articleType);
        sb.append(", articleAuthor=").append(articleAuthor);
        sb.append(", articleUrl=").append(articleUrl);
        sb.append(", articleOrder=").append(articleOrder);
        sb.append(", articleSiteId=").append(articleSiteId);
        sb.append(", articleCategoryId=").append(articleCategoryId);
        sb.append(", articleCreateTime=").append(articleCreateTime);
        sb.append(", articleUpdateTime=").append(articleUpdateTime);
        sb.append(", articleThumb=").append(articleThumb);
        sb.append(", articleHit=").append(articleHit);
        sb.append(", articleDesc=").append(articleDesc);
        sb.append(", articleStatus=").append(articleStatus);
        sb.append(", articleSkin=").append(articleSkin);
        sb.append(", articleInHomepage=").append(articleInHomepage);
        sb.append(", articleReleaseTime=").append(articleReleaseTime);
        sb.append(", articleReleaseStatus=").append(articleReleaseStatus);
        sb.append(", articleContent=").append(articleContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}