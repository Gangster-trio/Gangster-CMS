package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.QuestionPageService;
import com.gangster.cms.common.pojo.QuestionPage;
import com.gangster.cms.common.pojo.QuestionPageExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/25
 */
@Service
public class QuestionPageWebService {
    @Autowired
    private QuestionPageService questionPageService;

    public PageInfo<QuestionPage> listQuestionPage(Integer siteId, Integer page, Integer limit) {
        QuestionPageExample questionPageExample = new QuestionPageExample();
        questionPageExample.or().andQuestionSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> questionPageService.selectByExample(questionPageExample));
    }
}
