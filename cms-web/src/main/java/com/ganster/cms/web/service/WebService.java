package com.ganster.cms.web.service;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.TagService;
import com.ganster.cms.web.dto.ModelResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bigmeng
 */
@Service
public class WebService {
    private final
    SiteService siteService;

    private final
    ArticleService articleService;

    private final
    CategoryService categoryService;

    private final TagService tagService;

    public WebService(SiteService siteService, ArticleService articleService, CategoryService categoryService, TagService tagService) {
        this.siteService = siteService;
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    public ModelResult getSiteModel(String siteUrl) {
        ModelResult result = new ModelResult();

        //---------------------------------------default properties start----------------------------------------------//

        //get Site object
        SiteExample siteExample = new SiteExample();
        siteExample.or().andSiteUrlEqualTo(siteUrl);
        List<Site> siteList = siteService.selectByExample(siteExample);
        if (siteList.isEmpty()) {
            return null;
        }
        Site site = siteList.get(0);

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(site.getSiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(categoryService::toTree).collect(Collectors.toList());


        //Get a list of categories to put on the home page (with short article info)
        categoryExample.clear();
        categoryExample.or().andCategoryTypeEqualTo(CmsConst.INDEX_CATEGORY_TYPE).andCategorySiteIdEqualTo(site.getSiteId());
        List<Category> indexCategoryList = categoryService.selectByExample(categoryExample);


        //Get the list of articles to place on the homepage
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleTypeEqualTo(CmsConst.INDEX_ARTICLE_TYPE).andArticleSiteIdEqualTo(site.getSiteId());
        List<Article> articleList = articleService.selectByExample(articleExample);

        //Get the home carousel article
        articleExample.clear();
        articleExample.or().andArticleTypeEqualTo(CmsConst.CAROUSEL_ARTICLE_TYPE).andArticleSiteIdEqualTo(site.getSiteId());
        List<Article> carouselList = articleService.selectByExample(articleExample);

        //The default template needs the data
        result.put("categoryTreeList", categoryTreeList)
                .put("site", site)
                .put("indexCategoryList", indexCategoryList)
                .put("articleList", articleList)
                .put("carouselList", carouselList);


        //---------------------------------------custom properties start----------------------------------------------//


        /*
        The custom type category Need to pass to the home page
        key - type
        value - list of (category)
               map<"type",
                   list[
                       (category)->category type is "type"
                    ]
                >
        */
        categoryExample.clear();
        categoryExample.or().andCategoryInHomepageEqualTo(true).andCategorySiteIdEqualTo(site.getSiteId());
        result.getMap().putAll(categoryService.selectByExample(categoryExample).stream()
                .collect(Collectors.groupingBy(Category::getCategoryType)));

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
        articleExample.or().andArticleInHomepageEqualTo(true).andArticleSiteIdEqualTo(site.getSiteId());
        result.getMap().putAll(articleService.selectByExample(articleExample).parallelStream()
                .collect(Collectors.groupingBy(Article::getArticleType)));

        if (site.getSiteHit() == null) {
            site.setSiteHit(0);
        }
        site.setSiteHit(site.getSiteHit() + 1);
        siteService.updateByPrimaryKey(site);
        return result;
    }


    public ModelResult getCategoryModel(Integer id) {
        ModelResult result = new ModelResult();

        Category category = categoryService.selectByPrimaryKey(id);

        if (category == null) {
            return null;
        }


        //---------------------------------------default properties start----------------------------------------------//


        Site site = siteService.selectByPrimaryKey(category.getCategorySiteId());

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(category.getCategorySiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(categoryService::toTree).collect(Collectors.toList());

        //Get this category's article list (without BLOBs)
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(id);
        List<Article> articleList = articleService.selectByExample(articleExample);

        result.put("categoryTreeList", categoryTreeList)
                .put("articleList", articleList)
                .put("category", category)
                .put("site", site);

        Integer hit = category.getCategoryHit();
        if (hit == null) {
            category.setCategoryHit(0);
            hit = 0;
        }
        category.setCategoryHit(hit + 1);
        categoryService.updateByPrimaryKey(category);
        return result;
    }

    public ModelResult getArticleModel(Integer id) {
        Article article = articleService.selectByPrimaryKey(id);
        ModelResult result = new ModelResult();

        if (article == null) {
            return null;
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
        List<CategoryTree> categoryTreeList = categoryList.stream().map(categoryService::toTree).collect(Collectors.toList());

        result.put("category", category)
                .put("article", article)
                .put("tagList", tagList)
                .put("categoryTreeList", categoryTreeList)
                .put("site", site);

        //hit add
        if (article.getArticleHit() == null) {
            article.setArticleHit(0);
        }

        article.setArticleHit(article.getArticleHit() + 1);
        articleService.updateByPrimaryKeySelective(article);

        return result;
    }
}
