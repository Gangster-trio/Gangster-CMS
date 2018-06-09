package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class WebFile implements Serializable {
    private Integer fileId;

    private String fileName;

    private Date fileCreated;

    private Integer fileDownCount;

    private String fileType;

    private String fileSize;

    private Integer fileArticleId;

    private Integer fileSiteId;

    private Integer fileCategoryId;

    private static final long serialVersionUID = 1L;

    public WebFile(Integer fileId, String fileName, Date fileCreated, Integer fileDownCount, String fileType, String fileSize, Integer fileArticleId, Integer fileSiteId, Integer fileCategoryId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileCreated = fileCreated;
        this.fileDownCount = fileDownCount;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileArticleId = fileArticleId;
        this.fileSiteId = fileSiteId;
        this.fileCategoryId = fileCategoryId;
    }

    public WebFile(String fileName, Date fileCreated, Integer fileDownCount, String fileType, String fileSize, Integer fileArticleId, Integer fileSiteId, Integer fileCategoryId) {
        this.fileName = fileName;
        this.fileCreated = fileCreated;
        this.fileDownCount = fileDownCount;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.fileArticleId = fileArticleId;
        this.fileSiteId = fileSiteId;
        this.fileCategoryId = fileCategoryId;
    }

    public WebFile() {
        super();
    }

    public WebFile(String fileName, Date fileCreated, Integer fileDownCount, String fileType, String fileSize) {
        this.fileName = fileName;
        this.fileCreated = fileCreated;
        this.fileDownCount = fileDownCount;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getFileCreated() {
        return fileCreated;
    }

    public void setFileCreated(Date fileCreated) {
        this.fileCreated = fileCreated;
    }

    public Integer getFileDownCount() {
        return fileDownCount;
    }

    public void setFileDownCount(Integer fileDownCount) {
        this.fileDownCount = fileDownCount;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public Integer getFileArticleId() {
        return fileArticleId;
    }

    public void setFileArticleId(Integer fileArticleId) {
        this.fileArticleId = fileArticleId;
    }

    public Integer getFileSiteId() {
        return fileSiteId;
    }

    public void setFileSiteId(Integer fileSiteId) {
        this.fileSiteId = fileSiteId;
    }

    public Integer getFileCategoryId() {
        return fileCategoryId;
    }

    public void setFileCategoryId(Integer fileCategoryId) {
        this.fileCategoryId = fileCategoryId;
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
        return (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFileCreated() == null ? other.getFileCreated() == null : this.getFileCreated().equals(other.getFileCreated()))
            && (this.getFileDownCount() == null ? other.getFileDownCount() == null : this.getFileDownCount().equals(other.getFileDownCount()))
            && (this.getFileType() == null ? other.getFileType() == null : this.getFileType().equals(other.getFileType()))
            && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
            && (this.getFileArticleId() == null ? other.getFileArticleId() == null : this.getFileArticleId().equals(other.getFileArticleId()))
            && (this.getFileSiteId() == null ? other.getFileSiteId() == null : this.getFileSiteId().equals(other.getFileSiteId()))
            && (this.getFileCategoryId() == null ? other.getFileCategoryId() == null : this.getFileCategoryId().equals(other.getFileCategoryId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileCreated() == null) ? 0 : getFileCreated().hashCode());
        result = prime * result + ((getFileDownCount() == null) ? 0 : getFileDownCount().hashCode());
        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getFileArticleId() == null) ? 0 : getFileArticleId().hashCode());
        result = prime * result + ((getFileSiteId() == null) ? 0 : getFileSiteId().hashCode());
        result = prime * result + ((getFileCategoryId() == null) ? 0 : getFileCategoryId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileCreated=").append(fileCreated);
        sb.append(", fileDownCount=").append(fileDownCount);
        sb.append(", fileType=").append(fileType);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileArticleId=").append(fileArticleId);
        sb.append(", fileSiteId=").append(fileSiteId);
        sb.append(", fileCategoryId=").append(fileCategoryId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}