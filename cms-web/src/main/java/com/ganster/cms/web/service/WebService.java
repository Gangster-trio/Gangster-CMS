package com.ganster.cms.web.service;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.*;
import com.ganster.cms.web.cache.impl.HashMapCache;
import com.ganster.cms.web.dto.ModelResult;
import com.ganster.cms.web.util.CategoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bigmeng
 */
@Service
public class WebService {
    private final Logger logger = LoggerFactory.getLogger(WebService.class);

    private final
    SiteMapper siteMapper;

    private final
    ArticleMapper articleMapper;

    private final
    CategoryMapper categoryMapper;

    private final TagArticleMapper tagArticleMapper;

    private final TagMapper tagMapper;

    private HashMapCache<String, ModelResult> siteModelCache = new HashMapCache<>();
    private HashMapCache<Integer, ModelResult> articleModelCache = new HashMapCache<>();
    private HashMapCache<Integer, ModelResult> categoryModelCache = new HashMapCache<>();

    public WebService(SiteMapper siteMapper, ArticleMapper articleMapper, CategoryMapper categoryMapper, TagArticleMapper tagArticleMapper, TagMapper tagMapper) {
        this.siteMapper = siteMapper;
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.tagArticleMapper = tagArticleMapper;
        this.tagMapper = tagMapper;
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
        categoryExample.or().andCategorySiteIdEqualTo(site.getSiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(CategoryUtil::toTree).collect(Collectors.toList());


        //Get a list of categories to put on the home page (with short article info)
        categoryExample.clear();
        categoryExample.or().andCategoryTypeEqualTo(CmsConst.INDEX_CATEGORY_TYPE).andCategorySiteIdEqualTo(site.getSiteId());
        List<Category> indexCategoryList = categoryMapper.selectByExample(categoryExample);


        //Get the list of articles to place on the homepage
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleTypeEqualTo(CmsConst.INDEX_ARTICLE_TYPE).andArticleSiteIdEqualTo(site.getSiteId());
        List<Article> articleList = articleMapper.selectByExample(articleExample);

        //Get the home carousel article
        articleExample.clear();
        articleExample.or().andArticleTypeEqualTo(CmsConst.CAROUSEL_ARTICLE_TYPE).andArticleSiteIdEqualTo(site.getSiteId());
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
        categoryExample.or().andCategoryInHomepageEqualTo(true).andCategorySiteIdEqualTo(site.getSiteId());
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
        articleExample.or().andArticleInHomepageEqualTo(true).andArticleSiteIdEqualTo(site.getSiteId());
        result.getMap().putAll(articleMapper.selectByExample(articleExample).parallelStream()
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


    public ModelResult getCategoryModel(Integer id) {
        if (categoryModelCache.containsKey(id)) {
            ModelResult r = categoryModelCache.get(id);
            Category c = (Category) r.get("category");
            addCategoryHit(c);
            logger.info("get CategoryModel:{} from cache", id);
            return r;
        }

        ModelResult result = new ModelResult();

        Category category = categoryMapper.selectByPrimaryKey(id);

        if (category == null) {
            return null;
        }


        //---------------------------------------default properties start----------------------------------------------//


        Site site = siteMapper.selectByPrimaryKey(category.getCategorySiteId());

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(category.getCategorySiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(CategoryUtil::toTree).collect(Collectors.toList());

        //Get this category's article list (without BLOBs)
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(id);
        List<Article> articleList = articleMapper.selectByExample(articleExample);

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
        categoryMapper.updateByPrimaryKey(category);
    }

    public ModelResult getArticleModel(Integer id) {
        if (articleModelCache.containsKey(id)) {
            ModelResult r = articleModelCache.get(id);
            Article a = (Article) r.get("article");
            addArticleHit(a);
            logger.info("get ArticleModel:{} from cache", id);
            return r;
        }

        Article article = articleMapper.selectByPrimaryKey(id);
        ModelResult result = new ModelResult();

        if (article == null) {
            return null;
        }

        //Get article's site
        Site site = siteMapper.selectByPrimaryKey(article.getArticleSiteId());

        //Get article's category
        Category category = categoryMapper.selectByPrimaryKey(article.getArticleCategoryId());

        //Get article's tags
        TagArticleExample tagArticleExample = new TagArticleExample();
        tagArticleExample.or().andArticleIdEqualTo(id);
        List<Integer> tagIdList = tagArticleMapper.selectByExample(tagArticleExample).stream().map(TagArticle::getTagId).collect(Collectors.toList());
        List tagList;
        if (tagIdList.isEmpty()) {
            tagList = Collections.EMPTY_LIST;
        } else {
            TagExample tagExample = new TagExample();
            tagExample.or().andTagIdIn(tagIdList);
            tagList = tagMapper.selectByExample(tagExample);
        }
        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(article.getArticleSiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(CategoryUtil::toTree).collect(Collectors.toList());

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
        articleMapper.updateByPrimaryKeySelective(article);
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
