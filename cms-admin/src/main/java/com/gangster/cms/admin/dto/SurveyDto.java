package com.gangster.cms.admin.dto;

import com.gangster.cms.common.pojo.SurveyPage;

import java.io.Serializable;
import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/30
 */
public class SurveyDto implements Serializable {
    private Object page;
    private Object topicList;

    public SurveyDto(Object page) {
        this.page = page;
    }

    public SurveyDto(Object page, Object topicList) {
        this.page = page;
        this.topicList = topicList;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public Object getTopicList() {
        return topicList;
    }

    public void setTopicList(Object topicList) {
        this.topicList = topicList;
    }
}
