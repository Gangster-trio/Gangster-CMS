package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.SurveyWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey/topic")
public class SurveyTopicController {

    @Autowired
    private SurveyWebService surveyWebService;

    @DeleteMapping("/delete/{siteId}/{id}")
    public MessageDto delete(@SiteId @PathVariable Integer siteId, @PathVariable Integer id) {
        if (!surveyWebService.deleteSurveyTopic(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }
}
