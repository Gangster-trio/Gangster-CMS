package com.gangster.cms.web.directive;

import com.gangster.cms.dao.mapper.CategoryMapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategoryDirective implements TemplateDirectiveModel {

    private static final String PARAM_ID = "id";
    private static final String PARAM_RET = "ret";

    private final
    CategoryMapper categoryMapper;

    @Autowired
    public CategoryDirective(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException {
        Integer id = DirectiveUtil.getInteger(PARAM_ID, params);

        if (id == null) {
            throw new TemplateException("Must special id", env);
        }

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.getVersion());
        env.setVariable(DirectiveUtil.getRetName(PARAM_RET, params, PARAM_RET), builder.build().wrap(categoryMapper.selectByPrimaryKey(id)));
    }
}
