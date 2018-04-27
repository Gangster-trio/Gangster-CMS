package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.service.web.SurveyPageWebService;
import com.gangster.cms.common.pojo.SurveyPage;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@RestController
@RequestMapping("/survey/page")
public class SurveyPageController {

    @Autowired
    private SurveyPageWebService surveyPageWebService;

    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam Integer page, @RequestParam Integer limit) {
        PageInfo<SurveyPage> pageInfo = surveyPageWebService.listSurveyPage(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }
}
