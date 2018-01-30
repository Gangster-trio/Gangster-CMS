package com.ganster.cms.core.dao.pojo;

import java.util.Date;

public class Module {
    private Integer moduleId;

    private String moduleName;

    private String moduleUrl;

    private Integer moduleParentId;

    private Integer moduleSort;

    private Date moduleCreateTime;

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
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", moduleUrl='" + moduleUrl + '\'' +
                ", moduleParentId=" + moduleParentId +
                ", moduleSort=" + moduleSort +
                ", moduleCreateTime=" + moduleCreateTime +
                '}';
    }
}