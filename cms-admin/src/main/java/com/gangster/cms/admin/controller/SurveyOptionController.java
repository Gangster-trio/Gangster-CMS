package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.SurveyWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey/option")
public class SurveyOptionController {
    @Autowired
    private SurveyWebService surveyWebService;

    @DeleteMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {

        if (!surveyWebService.deleteSurveyOption(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }
}
