package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
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

    @DeleteMapping("/delete/{siteId}/{id}")
    @CmsPermission(moduleName = "问卷管理")
    public MessageDto delete(@SiteId @PathVariable Integer siteId, @PathVariable("id") Integer id) {
        if (!surveyWebService.deleteSurveyOption(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }
}
