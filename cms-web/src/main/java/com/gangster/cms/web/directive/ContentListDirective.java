package com.gangster.cms.web.directive;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.dao.mapper.ArticleMapper;
import com.gangster.cms.web.directive.util.DirectiveUtil;
import com.github.pagehelper.PageHelper;
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
public class ContentListDirective implements TemplateDirectiveModel {

    /**
     * 指定的栏目ID
     */
    private static final String PARAM_CID = "categoryId";

    /**
     * 每页的条目数，未指定默认为0
     */
    private static final String PARAM_SIZE = "size";

    /**
     * 页码数， 未指定默认为0
     */
    private static final String PARAM_PAGE = "page";

    /**
     * 排序方式，字符串形式，对应数据库中的字段，默认为{@link ContentListDirective#DEFAULT_SORT}
     */
    private static final String PARAM_SORT = "sort";

    /**
     * 默认排序方式：按创建时间排序
     */
    private static final String DEFAULT_SORT = "article_create_time";

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentListDirective.class);

    private final ArticleMapper articleMapper;

    @Autowired
    public ContentListDirective(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 返回指定目录的文章<br>
     */
    @Override
    public void execute(Environment env
            , Map params, TemplateModel[] loopVars
            , TemplateDirectiveBody body) throws TemplateException {
        Integer categoryId = DirectiveUtil.getInteger(PARAM_CID, params);
        Integer size = DirectiveUtil.getInteger(PARAM_SIZE, params);
        Integer page = DirectiveUtil.getInteger(PARAM_PAGE, params);
        String sort = DirectiveUtil.getString(PARAM_SORT, params);

        if (categoryId == null) {
            throw new TemplateException("Must specify " + PARAM_CID + "param.", env);
        }

        if (sort == null) {
            sort = DEFAULT_SORT;
        }

        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 0;
        }

        ArticleExample example = new ArticleExample();
        example.setOrderByClause(sort);
        example.or()
                .andArticleCategoryIdEqualTo(categoryId)
                .andArticleStatusEqualTo(CmsConst.ACCESS)
                .andArticleReleaseStatusEqualTo(true);
        List<Article> articleList = PageHelper.startPage(page, size)
                .doSelectPage(() -> articleMapper.selectByExample(example));
        DefaultObjectWrapper wrapper = new DefaultObjectWrapperBuilder(Configuration.getVersion()).build();

        try {
            for (Article article : articleList) {
                loopVars[0] = wrapper.wrap(article);
                body.render(env.getOut());

            }
        } catch (IOException e) {
            throw new TemplateException(e, env);
        }
    }
}
