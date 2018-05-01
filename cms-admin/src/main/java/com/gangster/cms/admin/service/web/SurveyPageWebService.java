package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.dto.SurveyDto;
import com.gangster.cms.admin.dto.SurveyTopicAndOptionDto;
import com.gangster.cms.admin.service.SurveyPageService;
import com.gangster.cms.common.dto.TopicWithOptionWrapper;
import com.gangster.cms.common.pojo.SurveyOption;
import com.gangster.cms.common.pojo.SurveyPage;
import com.gangster.cms.common.pojo.SurveyPageExample;
import com.gangster.cms.common.pojo.SurveyTopic;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@Service
public class SurveyPageWebService {
    private final SurveyPageService surveyPageService;

    @Autowired
    public SurveyPageWebService(SurveyPageService surveyPageService) {
        this.surveyPageService = surveyPageService;
    }

    public PageInfo<SurveyPage> listSurveyPage(Integer siteId, Integer page, Integer limit) {
        SurveyPageExample surveyPageExample = new SurveyPageExample();
        surveyPageExample.or().andPageSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> surveyPageService.selectByExample(surveyPageExample));
    }


    public boolean addSurveyPage(SurveyDto surveyDto) {
        Map<String, Object> map = surveyDtoTransformToMap(surveyDto);
        SurveyPage surveyPage = (SurveyPage) map.get("surveyPage");
        List<TopicWithOptionWrapper> topicWithOptionWrappers = (List<TopicWithOptionWrapper>) map.get("topicWithOptionWrappers");
        return surveyPageService.addSurveyPageWithTopicAndOptions(surveyPage, topicWithOptionWrappers);
    }

    public boolean deleteSurveyPage(Integer surveyPageId) {
        return surveyPageService.deleteSurveyPageWithTopicAndOptions(surveyPageId);
    }

    public boolean updateSurveyPage(SurveyDto surveyDto) {
        Map<String, Object> map = surveyDtoTransformToMap(surveyDto);
        SurveyPage surveyPage = (SurveyPage) map.get("surveyPage");
        List<TopicWithOptionWrapper> topicWithOptionWrappers = (List<TopicWithOptionWrapper>) map.get("topicWithOptionWrappers");
        return surveyPageService.updateSurveyPageWithTopicAndOptions(surveyPage, topicWithOptionWrappers);
    }

    public SurveyDto details(Integer surveyPageId) {
        Map<String, Object> map = surveyPageService.details(surveyPageId);
        return mapTransformToSurveyDto(map);
    }

    private Map<String, Object> surveyDtoTransformToMap(SurveyDto surveyDto) {
        Map<String, Object> map = new HashMap<>();
        SurveyPage surveyPage = surveyDto.getSurveyPage(surveyDto);
        List<SurveyTopicAndOptionDto> surveyTopicAndOptionDtos = surveyDto.getQuestions();
        List<TopicWithOptionWrapper> topicWithOptionWrappers = surveyTopicAndOptionDtos.stream().map(e -> {
            SurveyTopic surveyTopic = new SurveyTopic();
            surveyTopic.setTopicType(e.getTopicType());
            surveyTopic.setTopicQuestion(e.getTopicQuestion());
            List<SurveyOption> surveyOptions = Arrays.stream(e.getOptions()).map(SurveyOption::new).collect(Collectors.toList());
            return new TopicWithOptionWrapper(surveyTopic, surveyOptions);
        }).collect(Collectors.toList());
        map.put("surveyPage", surveyPage);
        map.put("topicWithOptionWrappers", topicWithOptionWrappers);
        return map;
    }

    private SurveyDto mapTransformToSurveyDto(Map<String, Object> map) {
        SurveyPage surveyPage = (SurveyPage) map.get("surveyPage");
        List<TopicWithOptionWrapper> topicWithOptionWrappers = (List<TopicWithOptionWrapper>) map.get("topicWithOptionWrappers");
        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setPageTitle(surveyPage.getPageTitle());
        surveyDto.setSiteId(String.valueOf(surveyPage.getPageSiteId()));
        surveyDto.setValidDate(transformDate(surveyPage.getPageCreateTime(), surveyPage.getPageEndTime()));
        List<SurveyTopicAndOptionDto> surveyTopicAndOptionDtos = topicWithOptionWrappers.stream().map(e -> {
            SurveyTopicAndOptionDto surveyTopicAndOptionDto = new SurveyTopicAndOptionDto();
            surveyTopicAndOptionDto.setTopicQuestion(e.getTopicQuestion());
            surveyTopicAndOptionDto.setTopicType(e.getTopicType());
            surveyTopicAndOptionDto.setOptions(e.getOptionList().stream().map(SurveyOption::getOptionContent).toArray(String[]::new));
            return surveyTopicAndOptionDto;
        }).collect(Collectors.toList());
        surveyDto.setQuestions(surveyTopicAndOptionDtos);
        return surveyDto;
    }

    private String transformDate(Date startTime, Date endTime) {
//        2018-05-03 00:00:00 ~ 2018-05-05 00:00:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(startTime) + " ~ " + simpleDateFormat.format(endTime);
    }
}
