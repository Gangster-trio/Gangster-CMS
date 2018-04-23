package com.gangster.cms.common.pojo;

import java.io.Serializable;

public class QuestionAnswer implements Serializable {
    private Integer answerId;

    private String answerContent;

    private Integer answerTopicId;

    private static final long serialVersionUID = 1L;

    public QuestionAnswer(Integer answerId, String answerContent, Integer answerTopicId) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.answerTopicId = answerTopicId;
    }

    public QuestionAnswer() {
        super();
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

    public Integer getAnswerTopicId() {
        return answerTopicId;
    }

    public void setAnswerTopicId(Integer answerTopicId) {
        this.answerTopicId = answerTopicId;
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
        QuestionAnswer other = (QuestionAnswer) that;
        return (this.getAnswerId() == null ? other.getAnswerId() == null : this.getAnswerId().equals(other.getAnswerId()))
            && (this.getAnswerContent() == null ? other.getAnswerContent() == null : this.getAnswerContent().equals(other.getAnswerContent()))
            && (this.getAnswerTopicId() == null ? other.getAnswerTopicId() == null : this.getAnswerTopicId().equals(other.getAnswerTopicId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnswerId() == null) ? 0 : getAnswerId().hashCode());
        result = prime * result + ((getAnswerContent() == null) ? 0 : getAnswerContent().hashCode());
        result = prime * result + ((getAnswerTopicId() == null) ? 0 : getAnswerTopicId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", answerId=").append(answerId);
        sb.append(", answerContent=").append(answerContent);
        sb.append(", answerTopicId=").append(answerTopicId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}