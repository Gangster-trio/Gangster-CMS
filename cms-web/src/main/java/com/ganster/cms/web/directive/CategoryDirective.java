package com.ganster.cms.web.directive;

import com.ganster.cms.core.service.CategoryService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CategoryDirective implements TemplateDirectiveModel {

    private static final String PARAM_ID = "id";
    private static final String PARAM_RET = "ret";

    private final
    CategoryService categoryService;

    @Autowired
    public CategoryDirective(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        Integer id = DirectiveUtil.getInteger(PARAM_ID, params);

        if (id == null) {
            throw new TemplateException("Must special id", env);
        }

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.getVersion());
        env.setVariable(DirectiveUtil.getRetName(PARAM_RET, params, PARAM_RET), builder.build().wrap(categoryService.selectByPrimaryKey(id)));
    }
}
