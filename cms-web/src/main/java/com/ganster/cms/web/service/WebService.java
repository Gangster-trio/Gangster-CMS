package com.ganster.cms.web.service;

import com.ganster.cms.core.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.TagService;
import com.ganster.cms.web.cache.impl.HashMapCache;
import com.ganster.cms.web.dto.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bigmeng
 */
@Service
public class WebService {
    private final Logger logger = LoggerFactory.getLogger(WebService.class);

    private final
    SiteService siteService;

    private final
    ArticleService articleService;

    private final
    CategoryService categoryService;

    private final TagService tagService;

    private HashMapCache<String, ModelResult> siteModelCache = new HashMapCache<>();
    private HashMapCache<Integer, ModelResult> articleModelCache = new HashMapCache<>();
    private HashMapCache<Integer, ModelResult> categoryModelCache = new HashMapCache<>();

    public WebService(SiteService siteService, ArticleService articleService, CategoryService categoryService, TagService tagService) {
        this.siteService = siteService;
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    public ModelResult getSiteModel(String siteUrl) {

        if (siteModelCache.containsKey(siteUrl)) {
            ModelResult r = siteModelCache.get(siteUrl);
            Site s = (Site) r.get("site");
            addSiteHit(s);
            logger.info("get SiteModel:{} from cache",siteUrl);
            return r;
        }

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

        addSiteHit(site);

        siteModelCache.put(siteUrl, result);
        return result;
    }

    private void addSiteHit(Site site) {
        if (site.getSiteHit() == null) {
            site.setSiteHit(0);
        }
        site.setSiteHit(site.getSiteHit() + 1);
        siteService.updateByPrimaryKey(site);
    }


    public ModelResult getCategoryModel(Integer id) {
        if (categoryModelCache.containsKey(id)) {
            ModelResult r = categoryModelCache.get(id);
            Category c = (Category) r.get("category");
            addCategoryHit(c);
            logger.info("get CategoryModel:{} from cache",id);
            return r;
        }

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

        addCategoryHit(category);

        categoryModelCache.put(id, result);
        return result;
    }

    private void addCategoryHit(Category category) {
        Integer hit = category.getCategoryHit();
        if (hit == null) {
            category.setCategoryHit(0);
            hit = 0;
        }
        category.setCategoryHit(hit + 1);
        categoryService.updateByPrimaryKey(category);
    }

    public ModelResult getArticleModel(Integer id) {
        if (articleModelCache.containsKey(id)) {
            ModelResult r = articleModelCache.get(id);
            Article a = (Article) r.get("article");
            addArticleHit(a);
            logger.info("get ArticleModel:{} from cache",id);
            return r;
        }

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

        addArticleHit(article);

        articleModelCache.put(id, result);
        return result;
    }

    private void addArticleHit(Article article) {
        //hit add
        if (article.getArticleHit() == null) {
            article.setArticleHit(0);
        }

        article.setArticleHit(article.getArticleHit() + 1);
        articleService.updateByPrimaryKeySelective(article);
    }

    //5分钟刷新一次缓存
    @Scheduled(fixedDelay = 1000 * 60 * 5)
    private void flushCache() {
        categoryModelCache.clear();
        articleModelCache.clear();
        siteModelCache.clear();
        logger.info("refresh cache");
    }
}
