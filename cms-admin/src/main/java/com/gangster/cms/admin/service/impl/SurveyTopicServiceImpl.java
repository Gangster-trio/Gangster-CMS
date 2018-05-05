package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.SurveyTopicService;
import com.gangster.cms.common.pojo.SurveyTopic;
import com.gangster.cms.common.pojo.SurveyTopicExample;
import com.gangster.cms.dao.mapper.SurveyOptionMapper;
import com.gangster.cms.dao.mapper.SurveyTopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@Service
public class SurveyTopicServiceImpl extends BaseServiceImpl<SurveyTopicMapper, SurveyTopic, SurveyTopicExample> implements SurveyTopicService {


    @Autowired
    private SurveyTopicMapper surveyTopicMapper;
    @Autowired
    private SurveyOptionMapper surveyOptionMapper;
    @Override
    public boolean deleteSurveyTopicWithOptions(Integer surveyTopicId) {
        return false;
    }

    @Override
    public boolean deleteSurveyTopicsWithOptions(List<Integer> surveyTopicIds) {
        return false;
    }
}
