package com.gangster.cms.web.directive;

import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
import com.gangster.cms.web.service.SurveyWebService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class SurveyDirective implements TemplateDirectiveModel {
    private static final String PARAM_PAGE_ID = "id";

    private final SurveyWebService surveyWebService;

    public SurveyDirective(SurveyWebService surveyWebService) {
        this.surveyWebService = surveyWebService;
    }

    @Override
    public void execute(Environment environment
            , Map map, TemplateModel[] loopVars
            , TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException {
        Integer pageId = DirectiveUtil.getInteger(PARAM_PAGE_ID, map);
        SurveyWithTopicWrapper survey = surveyWebService.getSurveyPage(pageId);

        DefaultObjectWrapper wrapper =
                new DefaultObjectWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build();

        if (loopVars.length == 0) {
            throw new TemplateException("必须指定一个循环变量,详细内容参见文档", environment);
        }

        loopVars[0] = wrapper.wrap(survey);

        try {
            templateDirectiveBody.render(environment.getOut());
        } catch (IOException e) {
            throw new TemplateException(e, environment);
        }
    }
}
