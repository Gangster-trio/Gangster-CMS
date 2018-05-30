package com.gangster.cms.web.directive;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.ArticleExample;
import com.gangster.cms.common.pojo.CategoryExample;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.dao.mapper.ArticleMapper;
import com.gangster.cms.dao.mapper.CategoryMapper;
import com.gangster.cms.web.directive.util.DirectiveUtil;
import com.github.pagehelper.PageHelper;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 获取指定类型的栏目或文章,栏目类型和文章类型只能指定一个
 */
@Component
public class TypeDirective implements TemplateDirectiveModel {

    /**
     * 指定栏目类型
     */
    private static final String PARAM_CATEGORY_TYPE = "cate_type";

    /**
     * 指定文章类型
     */
    private static final String PARAM_ARTICLE_TYPE = "article_type";

    /**
     * 页面大小，默认为0，返回所有数据
     */
    private static final String PARAM_SIZE = "size";

    /**
     * 页码数，默认为0
     */
    private static final String PARAM_PAGE = "page";

    /**
     * 排序方式，字符串形式，对应数据库中的字段
     */
    private static final String PARAM_SORT = "sort";

    /**
     * 未指定文章排序方式时的默认值
     */
    private static final String DEFAULT_ARTICLE_SORT = "article_create_time";

    /**
     * 未指定栏目排序方式是的默认值
     */
    private static final String DEFAULT_CATE_SORT = "category_create_time";

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public TypeDirective(ArticleMapper articleMapper, CategoryMapper categoryMapper) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars
            , TemplateDirectiveBody body) throws TemplateException {
        String cateType = DirectiveUtil.getString(PARAM_CATEGORY_TYPE, params);
        String articleType = DirectiveUtil.getString(PARAM_ARTICLE_TYPE, params);
        String sort = DirectiveUtil.getString(PARAM_SORT, params);
        Integer size = DirectiveUtil.getInteger(PARAM_SIZE, params);
        Integer page = DirectiveUtil.getInteger(PARAM_PAGE, params);
        Site site = DirectiveUtil.getSite(env);

        if (loopVars.length == 0) {
            throw new TemplateException("必须指定循环变量，详情参看文档", env);
        }

        if (site == null) {
            throw new TemplateException("site can't found", env);
        }

        //异或非运算 有且仅有一个不为null
        if ((cateType == null) == (articleType == null)) {
            throw new TemplateException(PARAM_ARTICLE_TYPE + " or " + PARAM_CATEGORY_TYPE + " must be specified one.", env);
        }

        if (size == null) {
            size = 0;
        }
        if (page == null) {
            page = 0;
        }
        if (sort == null) {
            if (cateType != null) sort = DEFAULT_CATE_SORT;
            if (articleType != null) sort = DEFAULT_ARTICLE_SORT;
        }

        List retList;
        if (cateType != null) {
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.or()
                    .andCategoryTypeEqualTo(cateType)
                    .andCategorySiteIdEqualTo(site.getSiteId())
                    .andCategoryStatusEqualTo(CmsConst.ACCESS);
            categoryExample.setOrderByClause(sort);
            retList = PageHelper.startPage(page, size)
                    .doSelectPage(() -> categoryMapper.selectByExample(categoryExample));
        } else {
            ArticleExample articleExample = new ArticleExample();
            articleExample.or()
                    .andArticleTypeEqualTo(articleType)
                    .andArticleSiteIdEqualTo(site.getSiteId())
                    .andArticleStatusEqualTo(CmsConst.ACCESS)
                    .andArticleReleaseStatusEqualTo(true);
            articleExample.setOrderByClause(sort);
            retList = PageHelper.startPage(page, size)
                    .doSelectPage(() -> articleMapper.selectByExample(articleExample));
        }

        DefaultObjectWrapper wrapper = new DefaultObjectWrapperBuilder(Configuration.getVersion()).build();

        try {
            for (Object a : retList) {
                loopVars[0] = wrapper.wrap(a);
                body.render(env.getOut());
            }
        } catch (IOException e) {
            throw new TemplateException(e, env);
        }
    }
}
