package com.gangster.cms.web.directive;

import com.gangster.cms.common.pojo.OuterChain;
import com.gangster.cms.common.pojo.OuterChainExample;
import com.gangster.cms.dao.mapper.OuterChainMapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class OuterChainDirective implements TemplateDirectiveModel {
    private static final String PARAM_SITE_ID = "siteId";

    @Autowired
    OuterChainMapper outerChainMapper;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException {
        if (loopVars.length == 0) {
            throw new TemplateException("请指定一个循环变量，详情参看文档", env);
        }

        Integer siteId = DirectiveUtil.getInteger(PARAM_SITE_ID, params);
        OuterChainExample example = new OuterChainExample();
        example.or().andOuterchainSiteIdEqualTo(siteId);
        List<OuterChain> outerChainList = outerChainMapper.selectByExample(example);
        DefaultObjectWrapper wrapper = new DefaultObjectWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS).build();

        try {
            for (OuterChain chain : outerChainList) {
                loopVars[0] = wrapper.wrap(chain);
                body.render(env.getOut());
            }
        } catch (IOException e) {
            throw new TemplateException(e, env);
        }
    }
}
