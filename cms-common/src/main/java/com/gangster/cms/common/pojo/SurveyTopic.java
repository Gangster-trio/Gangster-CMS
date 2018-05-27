package com.gangster.cms.common.pojo;

import java.io.Serializable;

public class SurveyTopic implements Serializable {
    private Integer topicId;

    private String topicQuestion;

    private String topicType;

    private Integer topicPageId;

    private static final long serialVersionUID = 1L;

    public SurveyTopic(Integer topicId, String topicQuestion, String topicType, Integer topicPageId) {
        this.topicId = topicId;
        this.topicQuestion = topicQuestion;
        this.topicType = topicType;
        this.topicPageId = topicPageId;
    }

    public SurveyTopic() {
        super();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicQuestion() {
        return topicQuestion;
    }

    public void setTopicQuestion(String topicQuestion) {
        this.topicQuestion = topicQuestion == null ? null : topicQuestion.trim();
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
    }

    public Integer getTopicPageId() {
        return topicPageId;
    }

    public void setTopicPageId(Integer topicPageId) {
        this.topicPageId = topicPageId;
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
        SurveyTopic other = (SurveyTopic) that;
        return (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicQuestion() == null ? other.getTopicQuestion() == null : this.getTopicQuestion().equals(other.getTopicQuestion()))
            && (this.getTopicType() == null ? other.getTopicType() == null : this.getTopicType().equals(other.getTopicType()))
            && (this.getTopicPageId() == null ? other.getTopicPageId() == null : this.getTopicPageId().equals(other.getTopicPageId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicQuestion() == null) ? 0 : getTopicQuestion().hashCode());
        result = prime * result + ((getTopicType() == null) ? 0 : getTopicType().hashCode());
        result = prime * result + ((getTopicPageId() == null) ? 0 : getTopicPageId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", topicId=").append(topicId);
        sb.append(", topicQuestion=").append(topicQuestion);
        sb.append(", topicType=").append(topicType);
        sb.append(", topicPageId=").append(topicPageId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}