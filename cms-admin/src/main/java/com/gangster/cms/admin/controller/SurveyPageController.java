package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.SurveyWebService;
import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
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
    private final SurveyWebService surveyPageWebService;

    @Autowired
    public SurveyPageController(SurveyWebService surveyPageWebService) {
        this.surveyPageWebService = surveyPageWebService;
    }

    @SystemControllerLog(description = "列出当前网站的所有问卷")
    @CmsPermission(moduleName = "问卷管理")
    @GetMapping("/list")
    public AjaxData list(
            @SiteId @RequestParam Integer siteId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<SurveyPage> pageInfo = surveyPageWebService.listSurveyPage(siteId, page, limit);
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "添加问卷")
    @CmsPermission(moduleName = "问卷管理")
    @PostMapping("/add/{siteId}")
    public MessageDto add(@SiteId @PathVariable Integer siteId, @RequestBody SurveyWithTopicWrapper wrapper) {
        if (wrapper != null) {
            if (!surveyPageWebService.addSurveyPage(wrapper)) {
                return MessageDto.fail(1, "failed");
            }
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "failed");
    }


    @SystemControllerLog(description = "删除某个问卷")
    @CmsPermission(moduleName = "问卷管理")
    @DeleteMapping("/delete/{siteId}/{id}")
    public MessageDto delete(@SiteId @PathVariable Integer siteId, @PathVariable("id") Integer id) {
        if (!surveyPageWebService.deleteSurveyPage(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "更新某个问卷")
    @Transactional
    @CmsPermission(moduleName = "问卷管理")
    @PostMapping("/update/{siteId}")
    public MessageDto update(@SiteId @PathVariable Integer siteId, @RequestBody SurveyWithTopicWrapper wrapper) {
        if (wrapper != null) {
            if (!surveyPageWebService.updateSurveyPage(wrapper)) {
                return MessageDto.fail(1, "failed");
            }
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "failed");
    }

    @SystemControllerLog(description = "查看某个问卷的详细信息")
    @GetMapping("/details/{siteId}/{id}")
    public MessageDto details(@SiteId @PathVariable Integer siteId, @PathVariable("id") Integer id) {
        return MessageDto.success(surveyPageWebService.detailsPage(id));
    }

    @SystemControllerLog(description = "问卷的统计信息")
    @GetMapping("/count/{siteId}/{id}")
    public MessageDto count(@SiteId @PathVariable Integer siteId, @PathVariable("id") Integer id) {
        return MessageDto.success(surveyPageWebService.countPage(id));
    }
}
