package com.gangster.cms.common.dto;

import com.gangster.cms.common.pojo.SurveyOption;
import com.gangster.cms.common.pojo.SurveyTopic;

import java.util.List;

public class TopicWithOptionWrapper extends SurveyTopic {
    public void setTopic(SurveyTopic topic) {
        this.topic = topic;
    }

    private SurveyTopic topic;
    private List<SurveyOption> optionList;

    public TopicWithOptionWrapper(SurveyTopic topic, List<SurveyOption> optionList) {
        this.topic = topic;
        this.optionList = optionList;
    }

    public SurveyTopic getTopic() {
        return topic;
    }

    @Override
    public Integer getTopicId() {
        return topic.getTopicId();
    }

    @Override
    public void setTopicId(Integer topicId) {
        topic.setTopicId(topicId);
    }

    @Override
    public String getTopicQuestion() {
        return topic.getTopicQuestion();
    }

    @Override
    public void setTopicQuestion(String topicQuestion) {
        topic.setTopicQuestion(topicQuestion);
    }

    @Override
    public String getTopicType() {
        return topic.getTopicType();
    }

    @Override
    public void setTopicType(String topicType) {
        topic.setTopicType(topicType);
    }

    @Override
    public Integer getTopicPageId() {
        return topic.getTopicPageId();
    }

    @Override
    public void setTopicPageId(Integer topicPageId) {
        topic.setTopicPageId(topicPageId);
    }

    public List<SurveyOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SurveyOption> optionList) {
        this.optionList = optionList;
    }

    public TopicWithOptionWrapper(Integer topicId, String topicQuestion, String topicType, Integer topicPageId, SurveyTopic topic) {
        super(topicId, topicQuestion, topicType, topicPageId);
        this.topic = topic;
    }

    public TopicWithOptionWrapper(Integer topicId, String topicQuestion, String topicType, Integer topicPageId, SurveyTopic topic, List<SurveyOption> optionList) {
        super(topicId, topicQuestion, topicType, topicPageId);
        this.topic = topic;
        this.optionList = optionList;
    }
}
