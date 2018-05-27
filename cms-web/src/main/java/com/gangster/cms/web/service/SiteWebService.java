package com.gangster.cms.web.service;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.*;
import com.gangster.cms.web.cache.impl.HashMapCache;
import com.gangster.cms.web.dto.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteWebService {
    private static final Logger logger = LoggerFactory.getLogger(SiteWebService.class);

    private final
    SiteMapper siteMapper;

    private final
    ArticleMapper articleMapper;

    private final
    CategoryMapper categoryMapper;

    private final
    CategoryWebService categoryWebService;

    private final HashMapCache<String, ModelResult> siteModelCache = new HashMapCache<>();

    public SiteWebService(SiteMapper siteMapper, ArticleMapper articleMapper, CategoryMapper categoryMapper, CategoryWebService categoryWebService) {
        this.siteMapper = siteMapper;
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.categoryWebService = categoryWebService;
    }

    public ModelResult getSiteModel(String siteUrl) {

        if (siteModelCache.containsKey(siteUrl)) {
            ModelResult r = siteModelCache.get(siteUrl);
            Site s = (Site) r.get("site");
            addSiteHit(s);
            logger.info("get SiteModel:{} from cache", siteUrl);
            return r;
        }

        ModelResult result = new ModelResult();

        //---------------------------------------default properties start----------------------------------------------//

        //get Site object
        SiteExample siteExample = new SiteExample();
        siteExample.or().andSiteUrlEqualTo(siteUrl);
        List<Site> siteList = siteMapper.selectByExample(siteExample);
        if (siteList.isEmpty()) {
            return null;
        }
        Site site = siteList.get(0);

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or()
                .andCategorySiteIdEqualTo(site.getSiteId())
                .andCategoryLevelEqualTo(0)
                .andCategoryStatusEqualTo(CmsConst.ACCESS);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(categoryWebService::toTree).collect(Collectors.toList());


        //Get a list of categories to put on the home page (with short article info)
        categoryExample.clear();
        categoryExample.or().andCategoryTypeEqualTo(CmsConst.INDEX_CATEGORY_TYPE).andCategorySiteIdEqualTo(site.getSiteId());
        List<Category> indexCategoryList = categoryMapper.selectByExample(categoryExample);


        //Get the list of articles to place on the homepage
        ArticleExample articleExample = new ArticleExample();
        articleExample.or()
                .andArticleTypeEqualTo(CmsConst.INDEX_ARTICLE_TYPE)
                .andArticleSiteIdEqualTo(site.getSiteId())
                .andArticleStatusEqualTo(CmsConst.ACCESS)
                .andArticleReleaseStatusEqualTo(true);
        List<Article> articleList = articleMapper.selectByExample(articleExample);

        //Get the home carousel article
        articleExample.clear();
        articleExample.or()
                .andArticleTypeEqualTo(CmsConst.CAROUSEL_ARTICLE_TYPE)
                .andArticleSiteIdEqualTo(site.getSiteId())
                .andArticleStatusEqualTo(CmsConst.ACCESS)
                .andArticleReleaseStatusEqualTo(true);
        List<Article> carouselList = articleMapper.selectByExample(articleExample);

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
        categoryExample.or()
                .andCategoryInHomepageEqualTo(true)
                .andCategorySiteIdEqualTo(site.getSiteId())
                .andCategoryStatusEqualTo(CmsConst.ACCESS);
        result.getMap().putAll(categoryMapper.selectByExample(categoryExample).stream()
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
        articleExample.or()
                .andArticleInHomepageEqualTo(true)
                .andArticleSiteIdEqualTo(site.getSiteId())
                .andArticleReleaseStatusEqualTo(true)
                .andArticleStatusEqualTo(CmsConst.ACCESS);
        result.getMap()
                .putAll(articleMapper.selectByExample(articleExample).parallelStream()
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
        siteMapper.updateByPrimaryKey(site);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void flushCache() {
        siteModelCache.clear();
        logger.info("refresh site cache");
    }
}
