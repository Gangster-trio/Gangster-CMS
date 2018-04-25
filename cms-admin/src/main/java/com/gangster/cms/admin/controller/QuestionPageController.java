package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.service.web.QuestionPageWebService;
import com.gangster.cms.common.pojo.QuestionPage;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yoke
 * Created on 2018/4/25
 */
@RestController
@RequestMapping("/question/page")
public class QuestionPageController {
    @Autowired
    private QuestionPageWebService questionPageWebService;

    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<QuestionPage> pageInfo = questionPageWebService.listQuestionPage(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }
}
