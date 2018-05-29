package com.gangster.cms.web.directive;

import com.gangster.cms.dao.mapper.CategoryMapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CategoryDirective implements TemplateDirectiveModel {

    private static final String PARAM_ID = "id";

    private final
    CategoryMapper categoryMapper;

    @Autowired
    public CategoryDirective(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars
            , TemplateDirectiveBody body) throws TemplateException {

        if (loopVars.length == 0) {
            throw new TemplateException("必须指定一个循环变量，详情参看档", env);
        }

        Integer id = DirectiveUtil.getInteger(PARAM_ID, params);

        if (id == null) {
            throw new TemplateException("Must special id", env);
        }

        DefaultObjectWrapper wrapper = new DefaultObjectWrapperBuilder(Configuration.getVersion()).build();
        loopVars[0] = wrapper.wrap(categoryMapper.selectByPrimaryKey(id));
        try {
            body.render(env.getOut());
        } catch (IOException e) {
            throw new TemplateException(e, env);
        }
    }
}
