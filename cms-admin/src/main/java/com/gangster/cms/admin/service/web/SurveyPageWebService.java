package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.SurveyPageService;
import com.gangster.cms.common.pojo.SurveyPage;
import com.gangster.cms.common.pojo.SurveyPageExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@Service
public class SurveyPageWebService {
    @Autowired
    private SurveyPageService surveyPageService;

    public PageInfo<SurveyPage> listSurveyPage(Integer siteId, Integer page, Integer limit) {
        SurveyPageExample surveyPageExample = new SurveyPageExample();
        surveyPageExample.or().andPageSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> surveyPageService.selectByExample(surveyPageExample));
    }
}
