package com.gangster.cms.web.controller;

import com.gangster.cms.web.dto.ModelResult;
import com.gangster.cms.web.service.SurveyWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/view/survey/")
public class SurveyController {
    private final SurveyWebService surveyWebService;

    public SurveyController(SurveyWebService surveyWebService) {
        this.surveyWebService = surveyWebService;
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        ModelResult result = surveyWebService.getQuestionModel(id);

        model.addAttribute("result", result);
        return "survey";
    }

    @PostMapping("submit/check")
    public void submitCheck(@RequestBody List<Integer> idList) {
        surveyWebService.submitCheck(idList);
    }

    @PostMapping("submit/text")
    public void submitText(@RequestBody Map<String ,String > textMap){
        surveyWebService.submitText(textMap);
    }

}
