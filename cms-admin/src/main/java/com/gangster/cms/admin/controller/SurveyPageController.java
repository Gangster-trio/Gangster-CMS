package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.dto.SurveyDto;
import com.gangster.cms.admin.service.web.SurveyPageWebService;
import com.gangster.cms.common.pojo.SurveyPage;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@RestController
@RequestMapping("/survey/page")
public class SurveyPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyPageController.class);
    private final SurveyPageWebService surveyPageWebService;

    @Autowired
    public SurveyPageController(SurveyPageWebService surveyPageWebService) {
        this.surveyPageWebService = surveyPageWebService;
    }

    @SystemControllerLog(description = "列出当前网站的素有问卷")
    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam Integer page, @RequestParam Integer limit) {
        PageInfo<SurveyPage> pageInfo = surveyPageWebService.listSurveyPage(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "添加问卷")
    @Transactional
    @PostMapping("/add")
    public MessageDto add(@RequestBody SurveyDto surveyDto) {
        if (!surveyPageWebService.addSurveyPage(surveyDto)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }


    @SystemControllerLog(description = "删除某个问卷")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (!surveyPageWebService.deleteSurveyPage(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "更新某个问卷")
    @Transactional
    @PostMapping("/update")
    public MessageDto update(@RequestBody SurveyDto surveyDto) {
        if (!surveyPageWebService.updateSurveyPage(surveyDto)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "查看某个问卷的详细信息")
    @GetMapping("/details/{id}")
    public MessageDto details(@PathVariable("id") Integer id) {
        return MessageDto.success(surveyPageWebService.details(id));
    }
}

