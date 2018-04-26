package com.gangster.cms.web.directive;

import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
import com.gangster.cms.web.service.SurveyWebService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class QuestionPageDirective implements TemplateDirectiveModel {
    private static final String PARAM_PAGE_ID = "id";
    private static final String PARAM_RET = "ret";

    private final SurveyWebService surveyWebService;

    public QuestionPageDirective(SurveyWebService surveyWebService) {
        this.surveyWebService = surveyWebService;
    }

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Integer pageId = DirectiveUtil.getInteger(PARAM_PAGE_ID, map);
        SurveyWithTopicWrapper survey = surveyWebService.getSurveyPage(pageId);

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        environment.setVariable(DirectiveUtil.getRetName(PARAM_RET, map), builder.build().wrap(survey));

        templateDirectiveBody.render(environment.getOut());
    }
}
