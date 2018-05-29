package com.gangster.cms.web.controller;

import com.gangster.cms.web.service.ArticleWebService;
import com.gangster.cms.web.service.CategoryWebService;
import com.gangster.cms.web.service.SiteWebService;
import com.gangster.cms.web.service.SurveyWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 在测试时使用， 访问url刷新内部缓存
 */
@Controller
public class FlushCacheController {
    @Autowired
    ArticleWebService articleWebService;
    @Autowired
    CategoryWebService categoryWebService;
    @Autowired
    SiteWebService siteWebService;
    @Autowired
    SurveyWebService surveyWebService;

    @GetMapping("/test/flush")
    public void flush() {
        articleWebService.flushCache();
        categoryWebService.flushCache();
        siteWebService.flushCache();
        surveyWebService.flushCache();
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
