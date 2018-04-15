package com.ganster.cms.web.directive;

import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.dao.mapper.ArticleMapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ArticleDirective implements TemplateDirectiveModel {
    private static final String PARAM_ID = "id";
    private static final String PARAM_BLOB = "blob";
    private static final String PARAM_RET = "ret";

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleDirective(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException {
        Integer id = DirectiveUtil.getInteger(PARAM_ID, params);
        Boolean blob = DirectiveUtil.getBoolean(PARAM_BLOB, params);

        if (blob == null) {
            blob = false;
        }

        if (id == null) {
            throw new TemplateException("Must special id", env);
        }

        Article article = null;
        ArticleExample example = new ArticleExample();
        example.or().andArticleIdEqualTo(id);
        if (blob) {
            List<Article> articleList = articleMapper.selectByExampleWithBLOBs(example);
            if (!articleList.isEmpty()) {
                article = articleList.get(0);
            }
        } else {
            List<Article> articles = articleMapper.selectByExample(example);
            if (!articles.isEmpty()) {
                article = articles.get(0);
            }
        }

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.getVersion());
        env.setVariable(DirectiveUtil.getRetName(PARAM_RET, params, PARAM_RET), builder.build().wrap(article));
    }
}
