package com.gangster.cms.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class QuestionPage implements Serializable {
    private Integer questionPageId;

    private String questionPageTitle;

    private Date questionPageCreateTime;

    private Date questionPageEndTime;

    private Integer questionSiteId;

    private static final long serialVersionUID = 1L;

    public QuestionPage(Integer questionPageId, String questionPageTitle, Date questionPageCreateTime, Date questionPageEndTime, Integer questionSiteId) {
        this.questionPageId = questionPageId;
        this.questionPageTitle = questionPageTitle;
        this.questionPageCreateTime = questionPageCreateTime;
        this.questionPageEndTime = questionPageEndTime;
        this.questionSiteId = questionSiteId;
    }

    public QuestionPage() {
        super();
    }

    public Integer getQuestionPageId() {
        return questionPageId;
    }

    public void setQuestionPageId(Integer questionPageId) {
        this.questionPageId = questionPageId;
    }

    public String getQuestionPageTitle() {
        return questionPageTitle;
    }

    public void setQuestionPageTitle(String questionPageTitle) {
        this.questionPageTitle = questionPageTitle == null ? null : questionPageTitle.trim();
    }

    public Date getQuestionPageCreateTime() {
        return questionPageCreateTime;
    }

    public void setQuestionPageCreateTime(Date questionPageCreateTime) {
        this.questionPageCreateTime = questionPageCreateTime;
    }

    public Date getQuestionPageEndTime() {
        return questionPageEndTime;
    }

    public void setQuestionPageEndTime(Date questionPageEndTime) {
        this.questionPageEndTime = questionPageEndTime;
    }

    public Integer getQuestionSiteId() {
        return questionSiteId;
    }

    public void setQuestionSiteId(Integer questionSiteId) {
        this.questionSiteId = questionSiteId;
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
        QuestionPage other = (QuestionPage) that;
        return (this.getQuestionPageId() == null ? other.getQuestionPageId() == null : this.getQuestionPageId().equals(other.getQuestionPageId()))
            && (this.getQuestionPageTitle() == null ? other.getQuestionPageTitle() == null : this.getQuestionPageTitle().equals(other.getQuestionPageTitle()))
            && (this.getQuestionPageCreateTime() == null ? other.getQuestionPageCreateTime() == null : this.getQuestionPageCreateTime().equals(other.getQuestionPageCreateTime()))
            && (this.getQuestionPageEndTime() == null ? other.getQuestionPageEndTime() == null : this.getQuestionPageEndTime().equals(other.getQuestionPageEndTime()))
            && (this.getQuestionSiteId() == null ? other.getQuestionSiteId() == null : this.getQuestionSiteId().equals(other.getQuestionSiteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getQuestionPageId() == null) ? 0 : getQuestionPageId().hashCode());
        result = prime * result + ((getQuestionPageTitle() == null) ? 0 : getQuestionPageTitle().hashCode());
        result = prime * result + ((getQuestionPageCreateTime() == null) ? 0 : getQuestionPageCreateTime().hashCode());
        result = prime * result + ((getQuestionPageEndTime() == null) ? 0 : getQuestionPageEndTime().hashCode());
        result = prime * result + ((getQuestionSiteId() == null) ? 0 : getQuestionSiteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", questionPageId=").append(questionPageId);
        sb.append(", questionPageTitle=").append(questionPageTitle);
        sb.append(", questionPageCreateTime=").append(questionPageCreateTime);
        sb.append(", questionPageEndTime=").append(questionPageEndTime);
        sb.append(", questionSiteId=").append(questionSiteId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}