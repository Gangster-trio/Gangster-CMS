package com.gangster.cms.web.service;

import com.gangster.cms.common.dto.CategoryTree;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.ArticleMapper;
import com.gangster.cms.dao.mapper.CategoryMapper;
import com.gangster.cms.dao.mapper.SiteMapper;
import com.gangster.cms.web.cache.CmsCache;
import com.gangster.cms.web.cache.impl.LRUCache;
import com.gangster.cms.web.dto.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryWebService {
    private final Logger logger = LoggerFactory.getLogger(CategoryWebService.class);

    private final
    SiteMapper siteMapper;

    private final
    ArticleMapper articleMapper;

    private final
    CategoryMapper categoryMapper;

    private CmsCache<Integer, ModelResult> categoryModelCache = new LRUCache<>(128);

    public CategoryWebService(SiteMapper siteMapper, ArticleMapper articleMapper, CategoryMapper categoryMapper) {
        this.siteMapper = siteMapper;
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
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


        //---------------------------------------default properties start----------------------------------------------//


        Site site = siteMapper.selectByPrimaryKey(category.getCategorySiteId());

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(category.getCategorySiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = categoryList.stream().map(this::toTree).collect(Collectors.toList());

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

    CategoryTree toTree(Category category) {
        CategoryTree tree = new CategoryTree();
        int columnId = category.getCategoryId();
        tree.setId(columnId);
        tree.setName(category.getCategoryTitle());
        tree.setSpread(false);
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategoryParentIdEqualTo(category.getCategoryId()).andCategoryStatusEqualTo(1);
        //子栏目
        List<Category> list = categoryMapper.selectByExample(categoryExample);
        if (list == null || list.isEmpty()) {
            tree.setChildren(null);
            return tree;
        }

        tree.setChildren(list.stream().map(this::toTree).collect(Collectors.toList()));

        return tree;
    }

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void flushCache() {
        categoryModelCache.clear();
        logger.info("refresh category cache");
    }
}
