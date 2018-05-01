package com.gangster.cms.admin.dto;

import com.gangster.cms.common.pojo.SurveyPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/30
 */
public class SurveyDto {
    private String pageTitle;
    private String validDate;
    private String siteId;
    private List<SurveyTopicAndOptionDto> questions;


    public SurveyPage getSurveyPage(SurveyDto surveyDto) {
        SurveyPage surveyPage = new SurveyPage();
        surveyPage.setPageTitle(pageTitle);
        String validDate = surveyDto.getValidDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] dates = validDate.split("~");
        String createTime = dates[0];
        String endTme = dates[1];
        try {
            Date pageCreateTime = sdf.parse(createTime);
            Date pageEndTime = sdf.parse(endTme);
            surveyPage.setPageCreateTime(pageCreateTime);
            surveyPage.setPageEndTime(pageEndTime);
            surveyPage.setPageSiteId(Integer.valueOf(surveyDto.getSiteId()));
            return surveyPage;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SurveyDto() {
    }

    public List<SurveyTopicAndOptionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyTopicAndOptionDto> questions) {
        this.questions = questions;
    }

    public SurveyDto(String pageTitle, String validDate, String siteId, List<SurveyTopicAndOptionDto> questions) {
        this.pageTitle = pageTitle;
        this.validDate = validDate;
        this.siteId = siteId;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "SurveyDto{" +
                "pageTitle='" + pageTitle + '\'' +
                ", validDate='" + validDate + '\'' +
                ", siteId='" + siteId + '\'' +
                ", questions=" + questions +
                '}';
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
}
