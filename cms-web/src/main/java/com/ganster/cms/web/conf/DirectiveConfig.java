package com.ganster.cms.web.conf;

import com.ganster.cms.web.directive.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DirectiveConfig {
    private final Configuration configuration;

    private final ContentListDirective contentListDirective;
    private final TypeDirective typeDirective;
    private final IndexArticleDirective indexArticleDirective;
    private final IndexCategoryDirective indexCategoryDirective;
    private final ArticleDirective articleDirective;
    private final CategoryDirective categoryDirective;

    @Autowired
    public DirectiveConfig(Configuration configuration, ContentListDirective contentListDirective, TypeDirective typeDirective, IndexArticleDirective indexArticleDirective, IndexCategoryDirective indexCategoryDirective, ArticleDirective articleDirective, CategoryDirective categoryDirective) {
        this.configuration = configuration;
        this.contentListDirective = contentListDirective;
        this.typeDirective = typeDirective;
        this.indexArticleDirective = indexArticleDirective;
        this.indexCategoryDirective = indexCategoryDirective;
        this.articleDirective = articleDirective;
        this.categoryDirective = categoryDirective;
    }

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("cms_content_list", contentListDirective);
        configuration.setSharedVariable("cms_type_list", typeDirective);
        configuration.setSharedVariable("cms_index_article", indexArticleDirective);
        configuration.setSharedVariable("cms_index_category", indexCategoryDirective);
        configuration.setSharedVariable("cms_article", articleDirective);
        configuration.setSharedVariable("cms_category", categoryDirective);
    }
}
