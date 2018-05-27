package com.gangster.cms.web.directive;

import com.gangster.cms.common.constant.CmsConst;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class IndexArticleDirective implements TemplateDirectiveModel {
    private static final String PARAM_ARTICLE_TYPE = "article_type";
    private final
    TypeDirective typeDirective;

    @Autowired
    public IndexArticleDirective(TypeDirective typeDirective) {
        this.typeDirective = typeDirective;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars
            , TemplateDirectiveBody body) throws TemplateException, IOException {
        params.put(PARAM_ARTICLE_TYPE, new SimpleScalar(CmsConst.INDEX_ARTICLE_TYPE));
        typeDirective.execute(env,params,loopVars,body);
    }
}
