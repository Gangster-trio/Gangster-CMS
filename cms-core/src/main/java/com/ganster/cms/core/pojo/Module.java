package com.ganster.cms.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class Module implements Serializable {
    private Integer moduleId;

    private String moduleName;

    private String moduleUrl;

    private Integer moduleParentId;

    private Integer moduleSort;

    private Date moduleCreateTime;

    private static final long serialVersionUID = 1L;

    public Module(Integer moduleId, String moduleName, String moduleUrl, Integer moduleParentId, Integer moduleSort, Date moduleCreateTime) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleUrl = moduleUrl;
        this.moduleParentId = moduleParentId;
        this.moduleSort = moduleSort;
        this.moduleCreateTime = moduleCreateTime;
    }

    public Module() {
        super();
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getModuleUrl() {
        return moduleUrl;
    }

    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl == null ? null : moduleUrl.trim();
    }

    public Integer getModuleParentId() {
        return moduleParentId;
    }

    public void setModuleParentId(Integer moduleParentId) {
        this.moduleParentId = moduleParentId;
    }

    public Integer getModuleSort() {
        return moduleSort;
    }

    public void setModuleSort(Integer moduleSort) {
        this.moduleSort = moduleSort;
    }

    public Date getModuleCreateTime() {
        return moduleCreateTime;
    }

    public void setModuleCreateTime(Date moduleCreateTime) {
        this.moduleCreateTime = moduleCreateTime;
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
        Module other = (Module) that;
        return (this.getModuleId() == null ? other.getModuleId() == null : this.getModuleId().equals(other.getModuleId()))
            && (this.getModuleName() == null ? other.getModuleName() == null : this.getModuleName().equals(other.getModuleName()))
            && (this.getModuleUrl() == null ? other.getModuleUrl() == null : this.getModuleUrl().equals(other.getModuleUrl()))
            && (this.getModuleParentId() == null ? other.getModuleParentId() == null : this.getModuleParentId().equals(other.getModuleParentId()))
            && (this.getModuleSort() == null ? other.getModuleSort() == null : this.getModuleSort().equals(other.getModuleSort()))
            && (this.getModuleCreateTime() == null ? other.getModuleCreateTime() == null : this.getModuleCreateTime().equals(other.getModuleCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getModuleId() == null) ? 0 : getModuleId().hashCode());
        result = prime * result + ((getModuleName() == null) ? 0 : getModuleName().hashCode());
        result = prime * result + ((getModuleUrl() == null) ? 0 : getModuleUrl().hashCode());
        result = prime * result + ((getModuleParentId() == null) ? 0 : getModuleParentId().hashCode());
        result = prime * result + ((getModuleSort() == null) ? 0 : getModuleSort().hashCode());
        result = prime * result + ((getModuleCreateTime() == null) ? 0 : getModuleCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", moduleId=").append(moduleId);
        sb.append(", moduleName=").append(moduleName);
        sb.append(", moduleUrl=").append(moduleUrl);
        sb.append(", moduleParentId=").append(moduleParentId);
        sb.append(", moduleSort=").append(moduleSort);
        sb.append(", moduleCreateTime=").append(moduleCreateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}