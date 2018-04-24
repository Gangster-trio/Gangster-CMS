package com.gangster.cms.common.pojo;

import java.io.Serializable;

public class QuestionTopic implements Serializable {
    private Integer topicId;

    private String topicQuestion;

    private String topicOption;

    private String topicType;

    private static final long serialVersionUID = 1L;

    public QuestionTopic(Integer topicId, String topicQuestion, String topicOption, String topicType) {
        this.topicId = topicId;
        this.topicQuestion = topicQuestion;
        this.topicOption = topicOption;
        this.topicType = topicType;
    }

    public QuestionTopic() {
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

    public String getTopicOption() {
        return topicOption;
    }

    public void setTopicOption(String topicOption) {
        this.topicOption = topicOption == null ? null : topicOption.trim();
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
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
        QuestionTopic other = (QuestionTopic) that;
        return (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getTopicQuestion() == null ? other.getTopicQuestion() == null : this.getTopicQuestion().equals(other.getTopicQuestion()))
            && (this.getTopicOption() == null ? other.getTopicOption() == null : this.getTopicOption().equals(other.getTopicOption()))
            && (this.getTopicType() == null ? other.getTopicType() == null : this.getTopicType().equals(other.getTopicType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getTopicQuestion() == null) ? 0 : getTopicQuestion().hashCode());
        result = prime * result + ((getTopicOption() == null) ? 0 : getTopicOption().hashCode());
        result = prime * result + ((getTopicType() == null) ? 0 : getTopicType().hashCode());
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
        sb.append(", topicOption=").append(topicOption);
        sb.append(", topicType=").append(topicType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}