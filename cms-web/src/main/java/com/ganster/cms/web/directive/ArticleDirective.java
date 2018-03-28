package com.ganster.cms.web.directive;

import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.service.ArticleService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ArticleDirective implements TemplateDirectiveModel {
    private static final String PARAM_ID = "id";
    private static final String PARAM_BLOB = "blob";
    private static final String PARAM_RET = "ret";

    private final ArticleService articleService;

    @Autowired
    public ArticleDirective(ArticleService articleService) {
        this.articleService = articleService;
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
            List<Article> articleList = articleService.selectByExampleWithBLOBs(example);
            if (!articleList.isEmpty()) {
                article = articleList.get(0);
            }
        } else {
            List<Article> articles = articleService.selectByExample(example);
            if (!articles.isEmpty()) {
                article = articles.get(0);
            }
        }

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.getVersion());
        env.setVariable(DirectiveUtil.getRetName(PARAM_RET, params, PARAM_RET), builder.build().wrap(article));
    }
}
