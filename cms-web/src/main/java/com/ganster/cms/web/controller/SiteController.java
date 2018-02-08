package com.ganster.cms.web.controller;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.web.dto.CategoryWithArticleList;
import com.ganster.cms.web.dto.ModelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteController {
    private final
    SiteService siteService;

    private final
    ArticleService articleService;

    private final
    CategoryService categoryService;

    @Autowired
    public SiteController(SiteService siteService, ArticleService articleService, CategoryService categoryService) {
        this.siteService = siteService;
        this.articleService = articleService;
        this.categoryService = categoryService;
    }


    @RequestMapping("/{siteUrl}")
    public String show(@PathVariable("siteUrl") String siteUrl, Model model) {

        ModelResult result = new ModelResult();

        //---------------------------------------default properties start----------------------------------------------//

        //get Site object
        SiteExample siteExample = new SiteExample();
        siteExample.or().andSiteUrlEqualTo(siteUrl);
        List<Site> siteList = siteService.selectByExample(siteExample);
        if (siteList == null || siteList.size() != 1) {
            return null;
        }
        Site site = siteList.get(0);

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(site.getSiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = new ArrayList<>();
        for (Category category : categoryList) {
            categoryTreeList.add(categoryService.toTree(category));
        }

        //Get a list of categories to put on the home page (with short article info)
        categoryExample.clear();
        categoryExample.or().andCategoryTypeEqualTo(CmsConst.INDEX_CATEGORY_TYPE).andCategorySiteIdEqualTo(site.getSiteId());
        List<Category> indexCategoryList = categoryService.selectByExample(categoryExample);
        if (indexCategoryList == null) {
            indexCategoryList = new ArrayList<>();
        }
        List<CategoryWithArticleList> categoryWithArticleList = new ArrayList<>();
        for (Category category : indexCategoryList) {
            ArticleExample example = new ArticleExample();
            example.or().andArticleCategoryIdEqualTo(category.getCategoryId());
            categoryWithArticleList.add(new CategoryWithArticleList(category, articleService.selectByExample(example)));
        }


        //Get the list of articles to place on the homepage
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleTypeEqualTo(CmsConst.INDEX_ARTICLE_TYPE);
        List<Article> articleList = articleService.selectByExample(articleExample);
        if (articleList == null) {
            articleList = new ArrayList<>();
        }

        //Get the home carousel article
        articleExample.clear();
        articleExample.or().andArticleTypeEqualTo(CmsConst.CAROUSEL_ARTICLE_TYPE);
        List<Article> carouselList = articleService.selectByExample(articleExample);
        if (carouselList == null) {
            carouselList = new ArrayList<>();
        }

        //The default template needs the data
        result.put("categoryTreeList", categoryTreeList)
                .put("site", site)
                .put("categoryWithArticleList", categoryWithArticleList)
                .put("articleList", articleList)
                .put("carouselList", carouselList);


        //---------------------------------------custom properties start----------------------------------------------//


        /*
        The custom type category Need to pass to the home page
        key - type
        value - list of (category with article list)
               map<"type",
                   list[
                       (category with article list)->category type is "type"
                    ]
                >
        */
        categoryExample.clear();
        categoryExample.or().andCategoryInHomepageEqualTo(true);
        List<Category> homePageCategoryList = categoryService.selectByExample(categoryExample);
        for (Category category : homePageCategoryList) {
            CategoryWithArticleList cwal = new CategoryWithArticleList(
                    category
                    , articleService.selectArticleByCategoryId(category.getCategoryId())
            );
            List list = (List) result.get(category.getCategoryType());
            //put (category with article list) to (home page category list)
            if (list == null) {
                list = new ArrayList<>();
                result.put(category.getCategoryType(), list);
            }
            list.add(cwal);
        }

        /*
        The custom type article Need to pass to the home page
        key - "type"
        value - list of (article->article type is "type")
              map<"type",
                  list[
                      article -> article type is "type"
                  ]
                 >
        */
        articleExample.clear();
        articleExample.or().andArticleInHomepageEqualTo(true);
        List<Article> homePageArticleList = articleService.selectByExample(articleExample);
        CategoryController.addListToResult(result, homePageArticleList);

        //Add result to module
        model.addAttribute("result",result);

        //If skin = null, set default skin
        if (site.getSiteSkin() == null) {
            site.setSiteSkin(CmsConst.DEFAULT_SKIN);
        }

        //Return to the site's skin view, for example : default-site
        return site.getSiteSkin() + CmsConst.SITE_SKIN_SUFFIX;
    }

    /**
     * map "/" to "/index"
     *
     * @return redirect
     */

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }
}
