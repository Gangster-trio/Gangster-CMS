package com.gangster.cms.admin.dto;

import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/30
 */
public class SurveyTopicAndOptionDto {
    private String topicQuestion;
    private String topicType;

    private String[] options;

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public SurveyTopicAndOptionDto() {
    }

    public SurveyTopicAndOptionDto(String topicQuestion, String topicType, String[] options) {
        this.topicQuestion = topicQuestion;
        this.topicType = topicType;
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
