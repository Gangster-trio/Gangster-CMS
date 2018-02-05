package com.ganster.cms.web.controller;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.TagService;
import com.ganster.cms.web.dto.ModelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/view/article/")
public class ArticleController {

    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final TagService tagService;

    private final SiteService siteService;

    @Autowired
    public ArticleController(ArticleService articleService, CategoryService categoryService, TagService tagService, SiteService siteService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.siteService = siteService;
    }

    @RequestMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Article article = articleService.selectByPrimaryKey(id);
        ModelResult result = new ModelResult();

        if (article == null){
            return "404";
        }

        //Get article's site
        Site site = siteService.selectByPrimaryKey(article.getArticleSiteId());

        //Get article's category
        Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());

        //Get article's tags
        List<Tag> tagList = tagService.selectByArticleId(id);

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(article.getArticleSiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = new ArrayList<>();
        for (Category c : categoryList) {
            categoryTreeList.add(categoryService.toTree(c));
        }

        result.put("category", category)
                .put("article", article)
                .put("tagList", tagList)
                .put("categoryTreeList", categoryTreeList)
                .put("site", site);


        model.addAttribute("result", result);

        //If skin = null, set default skin
        if (article.getArticleSkin() == null) {
            article.setArticleSkin(CmsConst.DEFAULT_SKIN);
        }

        //Return to the site's skin view, for example : default-article
        return article.getArticleSkin() + CmsConst.ARTICLE_SKIN_SUFFIX;
    }
}
