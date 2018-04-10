package com.ganster.cms.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class LogEntry implements Serializable {
    private Integer logId;

    private String logType;

    private Date logTime;

    private String logLevel;

    private Integer logSite;

    private String logInfo;

    private static final long serialVersionUID = 1L;

    public LogEntry(Integer logId, String logType, Date logTime, String logLevel, Integer logSite) {
        this.logId = logId;
        this.logType = logType;
        this.logTime = logTime;
        this.logLevel = logLevel;
        this.logSite = logSite;
    }

    public LogEntry(Integer logId, String logType, Date logTime, String logLevel, Integer logSite, String logInfo) {
        this.logId = logId;
        this.logType = logType;
        this.logTime = logTime;
        this.logLevel = logLevel;
        this.logSite = logSite;
        this.logInfo = logInfo;
    }

    public LogEntry() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType == null ? null : logType.trim();
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel == null ? null : logLevel.trim();
    }

    public Integer getLogSite() {
        return logSite;
    }

    public void setLogSite(Integer logSite) {
        this.logSite = logSite;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo == null ? null : logInfo.trim();
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
        LogEntry other = (LogEntry) that;
        return (this.getLogId() == null ? other.getLogId() == null : this.getLogId().equals(other.getLogId()))
            && (this.getLogType() == null ? other.getLogType() == null : this.getLogType().equals(other.getLogType()))
            && (this.getLogTime() == null ? other.getLogTime() == null : this.getLogTime().equals(other.getLogTime()))
            && (this.getLogLevel() == null ? other.getLogLevel() == null : this.getLogLevel().equals(other.getLogLevel()))
            && (this.getLogSite() == null ? other.getLogSite() == null : this.getLogSite().equals(other.getLogSite()))
            && (this.getLogInfo() == null ? other.getLogInfo() == null : this.getLogInfo().equals(other.getLogInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogId() == null) ? 0 : getLogId().hashCode());
        result = prime * result + ((getLogType() == null) ? 0 : getLogType().hashCode());
        result = prime * result + ((getLogTime() == null) ? 0 : getLogTime().hashCode());
        result = prime * result + ((getLogLevel() == null) ? 0 : getLogLevel().hashCode());
        result = prime * result + ((getLogSite() == null) ? 0 : getLogSite().hashCode());
        result = prime * result + ((getLogInfo() == null) ? 0 : getLogInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logId=").append(logId);
        sb.append(", logType=").append(logType);
        sb.append(", logTime=").append(logTime);
        sb.append(", logLevel=").append(logLevel);
        sb.append(", logSite=").append(logSite);
        sb.append(", logInfo=").append(logInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}