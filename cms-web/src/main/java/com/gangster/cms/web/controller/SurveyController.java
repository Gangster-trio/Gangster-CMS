package com.gangster.cms.web.controller;

import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
import com.gangster.cms.web.dto.ModelResult;
import com.gangster.cms.web.service.SurveyWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/view/survey/")
public class SurveyController {
    private final SurveyWebService surveyWebService;
    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

    public SurveyController(SurveyWebService surveyWebService) {
        this.surveyWebService = surveyWebService;
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        ModelResult result = surveyWebService.getQuestionModel(id);

        model.addAttribute("result", result);
        SurveyWithTopicWrapper page = (SurveyWithTopicWrapper) result.get("page");

        if (page != null) {
            if (page.getPage().getPageSkinName() == null) {
                logger.error("{} skin name is null", page.getPage().getPageTitle());
                return "error/404";
            } else {
                return page.getPage().getPageSkinName();
            }
        } else {
            return "error/404";
        }
    }

    @PostMapping("submit/check")
    public void submitCheck(@RequestBody List<Integer> idList) {
        surveyWebService.submitCheck(idList);
    }

    @PostMapping("submit/text")
    public void submitText(@RequestBody Map<Integer, String> textMap) {
        surveyWebService.submitText(textMap);
    }

}
