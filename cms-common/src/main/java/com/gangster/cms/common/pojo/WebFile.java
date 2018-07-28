package com.gangster.cms.common.pojo;

import java.io.Serializable;

public class WebFile implements Serializable {
    private Integer fileArticleId;

    private String fileKey;

    private Integer fileSiteId;

    private static final long serialVersionUID = 1L;

    public WebFile(Integer fileArticleId, String fileKey, Integer fileSiteId) {
        this.fileArticleId = fileArticleId;
        this.fileKey = fileKey;
        this.fileSiteId = fileSiteId;
    }

    public WebFile() {
        super();
    }

    public Integer getFileArticleId() {
        return fileArticleId;
    }

    public void setFileArticleId(Integer fileArticleId) {
        this.fileArticleId = fileArticleId;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey == null ? null : fileKey.trim();
    }

    public Integer getFileSiteId() {
        return fileSiteId;
    }

    public void setFileSiteId(Integer fileSiteId) {
        this.fileSiteId = fileSiteId;
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
        WebFile other = (WebFile) that;
        return (this.getFileArticleId() == null ? other.getFileArticleId() == null : this.getFileArticleId().equals(other.getFileArticleId()))
            && (this.getFileKey() == null ? other.getFileKey() == null : this.getFileKey().equals(other.getFileKey()))
            && (this.getFileSiteId() == null ? other.getFileSiteId() == null : this.getFileSiteId().equals(other.getFileSiteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFileArticleId() == null) ? 0 : getFileArticleId().hashCode());
        result = prime * result + ((getFileKey() == null) ? 0 : getFileKey().hashCode());
        result = prime * result + ((getFileSiteId() == null) ? 0 : getFileSiteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileArticleId=").append(fileArticleId);
        sb.append(", fileKey=").append(fileKey);
        sb.append(", fileSiteId=").append(fileSiteId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}