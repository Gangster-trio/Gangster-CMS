package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.SurveyPageService;
import com.gangster.cms.common.dto.TopicWithOptionWrapper;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.SurveyOptionMapper;
import com.gangster.cms.dao.mapper.SurveyPageMapper;
import com.gangster.cms.dao.mapper.SurveyTopicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@Service
public class SurveyPageServiceImpl extends BaseServiceImpl<SurveyPageMapper, SurveyPage, SurveyPageExample> implements SurveyPageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyPageServiceImpl.class);

    @Autowired
    private SurveyTopicMapper surveyTopicMapper;
    @Autowired
    private SurveyOptionMapper surveyOptionMapper;


    @Override
    public boolean addSurveyPageWithTopicAndOptions(SurveyPage surveyPage, List<TopicWithOptionWrapper> topicWithOptionWrappers) {
        try {
            insert(surveyPage);
            topicWithOptionWrappers.forEach(e -> {
                SurveyTopic surveyTopic = e.getTopic();
                surveyTopic.setTopicPageId(surveyPage.getPageId());
                surveyTopicMapper.insert(surveyTopic);
                e.getOptionList().forEach(t -> {
                    t.setTopicId(surveyTopic.getTopicId());
                    t.setOptionCount(0);
                    surveyOptionMapper.insert(t);
                });
            });
        } catch (Exception e) {
            LOGGER.error("添加问卷时插入失败,错误信息 {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSurveyPageWithTopicAndOptions(Integer surveyPageId) {
        SurveyPage surveyPage = selectByPrimaryKey(surveyPageId);
        try {
            if (surveyPage != null) {
                SurveyTopicExample surveyTopicExample = new SurveyTopicExample();
                surveyTopicExample.or().andTopicPageIdEqualTo(surveyPageId);
                List<Integer> surveyTopicIds = surveyTopicMapper.selectByExample(surveyTopicExample).stream().map(SurveyTopic::getTopicPageId).collect(Collectors.toList());
                if (surveyTopicIds != null) {
                    SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
                    surveyOptionExample.or().andTopicIdIn(surveyTopicIds);
                    surveyOptionMapper.deleteByExample(surveyOptionExample);
                    surveyTopicMapper.deleteByExample(surveyTopicExample);
                }
                deleteByPrimaryKey(surveyPageId);
            }
        } catch (Exception e) {
            LOGGER.error("删除问卷时出错,错误信息：{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSurveyPageWithTopicAndOptions(SurveyPage surveyPage, List<TopicWithOptionWrapper> topicWithOptionWrappers) {
        try {
            updateByPrimaryKeySelective(surveyPage);
            topicWithOptionWrappers.forEach(e -> {
                SurveyTopic surveyTopic = e.getTopic();
                if (null == surveyTopic.getTopicId()) {
                    surveyTopic.setTopicPageId(surveyPage.getPageId());
                    surveyTopicMapper.insert(e);
                    e.getOptionList().forEach(t -> {
                        t.setTopicId(surveyTopic.getTopicId());
                        t.setOptionCount(0);
                        surveyOptionMapper.insert(t);
                    });
                } else {
                    surveyTopicMapper.updateByPrimaryKeySelective(surveyTopic);
                    e.getOptionList().forEach(t -> {
                        t.setOptionCount(0);
                        if (null == t.getOptionId()) {
                            surveyOptionMapper.insert(t);
                        } else {
                            surveyOptionMapper.updateByPrimaryKeySelective(t);
                        }
                    });
                }
            });
        } catch (Exception e) {
            LOGGER.error("更新问卷失败，失败信息：{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> details(Integer surveyPageId) {
        Map<String, Object> map = new HashMap<>();
        SurveyPage surveyPage = selectByPrimaryKey(surveyPageId);
        map.put("surveyPage", surveyPage);
        SurveyTopicExample surveyTopicExample = new SurveyTopicExample();
        SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
        surveyTopicExample.or().andTopicPageIdEqualTo(surveyPageId);
        List<TopicWithOptionWrapper> topicWithOptionWrappers = surveyTopicMapper.selectByExample(surveyTopicExample).stream().map(e -> {
            surveyOptionExample.clear();
            surveyOptionExample.or().andTopicIdEqualTo(e.getTopicId());
            return new TopicWithOptionWrapper(e, surveyOptionMapper.selectByExample(surveyOptionExample));
        }).collect(Collectors.toList());
        map.put("topicWithOptionWrappers", topicWithOptionWrappers);
        return map;
    }
}
