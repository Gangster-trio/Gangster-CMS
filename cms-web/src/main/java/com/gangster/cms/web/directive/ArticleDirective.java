package com.gangster.cms.web.directive;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.dao.mapper.ArticleMapper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ArticleDirective implements TemplateDirectiveModel {
    private static final String PARAM_ID = "id";
    private static final String PARAM_BLOB = "blob";

    public static final Logger LOGGER = LoggerFactory.getLogger(ArticleDirective.class);

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleDirective(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * @param env
     * @param params
     * @param loopVars
     * @param body
     * @throws TemplateException
     */
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars
            , TemplateDirectiveBody body) throws TemplateException {

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
        example.or()
                .andArticleIdEqualTo(id)
                .andArticleReleaseStatusEqualTo(true)
                .andArticleStatusEqualTo(CmsConst.ACCESS);

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


        if (article == null) {
            LOGGER.error("id为 {} 的文章不存在！", id);
        }

        DefaultObjectWrapper wrapper = new DefaultObjectWrapperBuilder(Configuration.getVersion()).build();
        loopVars[0] = wrapper.wrap(article);
        try {
            body.render(env.getOut());
        } catch (IOException e) {
            throw new TemplateException(e, env);
        }
    }
}
