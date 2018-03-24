package com.ganster.cms.web.conf;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.web.directive.ContentListDirective;
import com.ganster.cms.web.directive.IndexArticleDirective;
import com.ganster.cms.web.directive.IndexCategoryDirective;
import com.ganster.cms.web.directive.TypeDirective;
import freemarker.ext.beans.BeansWrapper;
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

    @Autowired
    public DirectiveConfig(Configuration configuration, ContentListDirective contentListDirective, TypeDirective typeDirective, IndexArticleDirective indexArticleDirective, IndexCategoryDirective indexCategoryDirective) {
        this.configuration = configuration;
        this.contentListDirective = contentListDirective;
        this.typeDirective = typeDirective;
        this.indexArticleDirective = indexArticleDirective;
        this.indexCategoryDirective = indexCategoryDirective;
    }

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("cms_content_list", contentListDirective);
        configuration.setSharedVariable("cms_type_list", typeDirective);
        configuration.setSharedVariable("cms_index_article", indexArticleDirective);
        configuration.setSharedVariable("cms_index_category", indexCategoryDirective);
    }
}
