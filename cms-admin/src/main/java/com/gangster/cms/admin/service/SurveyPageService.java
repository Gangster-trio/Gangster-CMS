package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.dto.TopicWithOptionWrapper;
import com.gangster.cms.common.pojo.SurveyPage;
import com.gangster.cms.common.pojo.SurveyPageExample;

import java.util.List;
import java.util.Map;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
public interface SurveyPageService extends BaseService<SurveyPage, SurveyPageExample> {

    boolean addSurveyPageWithTopicAndOptions(SurveyPage surveyPage, List<TopicWithOptionWrapper> topicWithOptionWrappers);


    boolean deleteSurveyPageWithTopicAndOptions(Integer surveyPageId);

    boolean updateSurveyPageWithTopicAndOptions(SurveyPage surveyPage, List<TopicWithOptionWrapper> topicWithOptionWrappers);

    Map<String,Object> details(Integer surveyPageId);
}
