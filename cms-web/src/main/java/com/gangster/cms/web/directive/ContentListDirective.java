package com.gangster.cms.web.directive;

import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.dao.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 自定义模板方法 cms_content_list,
 * 返回指定目录的指定文章<br>
 * 参数:<br>
 * {@link ContentListDirective#PARAM_CID}:指定目录ID<br>
 * {@link ContentListDirective#PARAM_PAGE}:页数 默认为0<br>
 * {@link ContentListDirective#PARAM_SIZE}:返回的条目数<br>
 * {@link ContentListDirective#PARAM_SORT}:返回文章的排序方式 默认按创建时间排序({@link ContentListDirective#DEFAULT_SORT})，该参数为数据库字段名(支持asc升序,desc降序)<br>
 * {@link ContentListDirective#PARAM_RET}:返回的model名称，默认为{@link ContentListDirective#PARAM_RET}<br>
 */
@Component
public class ContentListDirective implements TemplateDirectiveModel {
    private static final String PARAM_CID = "categoryId";
    private static final String PARAM_SIZE = "size";
    private static final String PARAM_PAGE = "page";
    private static final String PARAM_SORT = "sort";
    private static final String PARAM_RET = "ret";

    private static final String DEFAULT_SORT = "article_create_time";

    private final ArticleMapper articleMapper;

    @Autowired
    public ContentListDirective(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
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
            size = 5;
        }

        ArticleExample example = new ArticleExample();
        example.setOrderByClause(sort);
        example.or().andArticleCategoryIdEqualTo(categoryId);
        List<Article> articleList = PageHelper.startPage(page, size).doSelectPage(() -> articleMapper.selectByExample(example));

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.getVersion());
        env.setVariable(DirectiveUtil.getRetName(PARAM_RET, params), builder.build().wrap(articleList));
        body.render(env.getOut());
    }
}
