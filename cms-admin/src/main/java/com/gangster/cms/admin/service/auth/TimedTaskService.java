package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.TagService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class TimedTaskService {
    private static final Logger logger = LoggerFactory.getLogger(TimedTaskService.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    public boolean addArticleTimedTaskController(ArticleDTO articleDTO) {
        Category category = categoryService.selectByPrimaryKey(articleDTO.toArticle().getArticleCategoryId());
        Integer sid = category.getCategorySiteId();
        Article article = articleDTO.toArticle();
        article.setArticleCreateTime(new Date());
        article.setArticleSiteId(sid);
        article.setArticleStatus(CmsConst.REVIEW);
        int count = articleService.insertSelectiveWithTag(article, Arrays.asList(articleDTO.getTags().split(",")));
        return count != 0;
    }
}
