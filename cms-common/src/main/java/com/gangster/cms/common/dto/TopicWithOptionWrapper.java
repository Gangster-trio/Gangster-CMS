package com.gangster.cms.common.dto;

import com.gangster.cms.common.pojo.SurveyOption;
import com.gangster.cms.common.pojo.SurveyTopic;

import java.util.List;

public class TopicWithOptionWrapper{
    private SurveyTopic topic;
    private List<SurveyOption> optionList;

    public TopicWithOptionWrapper(SurveyTopic topic) {
        this.topic = topic;
    }

    public TopicWithOptionWrapper(SurveyTopic topic, List<SurveyOption> optionList) {
        this.topic = topic;
        this.optionList = optionList;
    }

    public TopicWithOptionWrapper(){}
    public SurveyTopic getTopic() {
        return topic;
    }

    public void setTopic(SurveyTopic topic) {
        this.topic = topic;
    }

    public List<SurveyOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SurveyOption> optionList) {
        this.optionList = optionList;
    }
}
