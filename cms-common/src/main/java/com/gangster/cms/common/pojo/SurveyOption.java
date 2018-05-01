package com.gangster.cms.common.pojo;

import java.io.Serializable;

public class SurveyOption implements Serializable {
    private Integer optionId;

    private String optionContent;

    private Integer topicId;

    private Integer optionCount;

    private static final long serialVersionUID = 1L;

    public SurveyOption(String optionContent, Integer topicId) {
        this.optionContent = optionContent;
        this.topicId = topicId;
    }

    public SurveyOption(String optionContent) {
        this.optionContent = optionContent;
    }

    public SurveyOption(Integer optionId, String optionContent, Integer topicId, Integer optionCount) {
        this.optionId = optionId;
        this.optionContent = optionContent;
        this.topicId = topicId;
        this.optionCount = optionCount;
    }

    public SurveyOption() {
        super();
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent == null ? null : optionContent.trim();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getOptionCount() {
        return optionCount;
    }

    public void setOptionCount(Integer optionCount) {
        this.optionCount = optionCount;
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
        SurveyOption other = (SurveyOption) that;
        return (this.getOptionId() == null ? other.getOptionId() == null : this.getOptionId().equals(other.getOptionId()))
            && (this.getOptionContent() == null ? other.getOptionContent() == null : this.getOptionContent().equals(other.getOptionContent()))
            && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
            && (this.getOptionCount() == null ? other.getOptionCount() == null : this.getOptionCount().equals(other.getOptionCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOptionId() == null) ? 0 : getOptionId().hashCode());
        result = prime * result + ((getOptionContent() == null) ? 0 : getOptionContent().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getOptionCount() == null) ? 0 : getOptionCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", optionId=").append(optionId);
        sb.append(", optionContent=").append(optionContent);
        sb.append(", topicId=").append(topicId);
        sb.append(", optionCount=").append(optionCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}