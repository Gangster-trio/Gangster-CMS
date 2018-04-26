package com.gangster.cms.web.controller;

import com.gangster.cms.web.dto.ModelResult;
import com.gangster.cms.web.service.SurveyWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/survey/")
public class SurveyController {
    private final SurveyWebService surveyWebService;

    public SurveyController(SurveyWebService surveyWebService) {
        this.surveyWebService = surveyWebService;
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id,Model model){
        ModelResult result = surveyWebService.getQuestionModel(id);

        model.addAttribute("result",result);
        return "survey";
    }
}
