package com.gangster.cms.common.dto;

import com.gangster.cms.common.pojo.SurveyPage;

import java.util.Date;
import java.util.List;

public class SurveyWithTopicWrapper extends SurveyPage {
    private List<TopicWithOptionWrapper> topicList;
    private SurveyPage page;

    public SurveyWithTopicWrapper(SurveyPage page, List<TopicWithOptionWrapper> topicList) {
        this.page = page;
        this.topicList = topicList;
    }

    @Override
    public Integer getPageId() {
        return page.getPageId();
    }

    @Override
    public void setPageId(Integer pageId) {
        page.setPageId(pageId);
    }

    @Override
    public String getPageTitle() {
        return page.getPageTitle();
    }

    @Override
    public void setPageTitle(String pageTitle) {
        page.setPageTitle(pageTitle);
    }

    @Override
    public Date getPageCreateTime() {
        return page.getPageCreateTime();
    }

    @Override
    public void setPageCreateTime(Date pageCreateTime) {
        page.setPageCreateTime(pageCreateTime);
    }

    @Override
    public Date getPageEndTime() {
        return page.getPageEndTime();
    }

    @Override
    public void setPageEndTime(Date pageEndTime) {
        page.setPageEndTime(pageEndTime);
    }

    @Override
    public Integer getPageSiteId() {
        return page.getPageSiteId();
    }

    @Override
    public void setPageSiteId(Integer pageSiteId) {
        page.setPageSiteId(pageSiteId);
    }

    public List<TopicWithOptionWrapper> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicWithOptionWrapper> topicList) {
        this.topicList = topicList;
    }

    public void setPage(SurveyPage page) {
        this.page = page;
    }
}
