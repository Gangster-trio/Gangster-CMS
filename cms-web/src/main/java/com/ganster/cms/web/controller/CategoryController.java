package com.ganster.cms.web.controller;

import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.*;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.web.dto.ModelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/view/category/")
public class CategoryController {
    private final CategoryService categoryService;
    private final ArticleService articleService;
    private final SiteService siteService;


    @Autowired
    public CategoryController(CategoryService categoryService, ArticleService articleService, SiteService siteService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.siteService = siteService;
    }

    //Extract reusable methods
    static void addListToResult(ModelResult result, List<Article> homePageArticleList) {
        for (Article article : homePageArticleList) {
            List list = (List) result.get(article.getArticleType());
            if (list == null) {
                list = new ArrayList();
                result.put(article.getArticleType(), list);
            }
            list.add(article);
        }
    }

    @RequestMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        ModelResult result = new ModelResult();

        Category category = categoryService.selectByPrimaryKey(id);

        if (category==null){
            return "404";
        }


        //---------------------------------------default properties start----------------------------------------------//


        Site site = siteService.selectByPrimaryKey(category.getCategorySiteId());

        //Get 0 level categorise in this site (displayed above the homepage of the website)
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.or().andCategorySiteIdEqualTo(category.getCategorySiteId()).andCategoryLevelEqualTo(0);
        List<Category> categoryList = categoryService.selectByExample(categoryExample);
        //Each level 0 category into category tree
        List<CategoryTree> categoryTreeList = new ArrayList<>();
        for (Category c : categoryList) {
            categoryTreeList.add(categoryService.toTree(c));
        }

        //Get this category's article list (without BLOBs)
        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(id);
        List<Article> articleList = articleService.selectByExample(articleExample);

        result.put("categoryTreeList", categoryTreeList)
                .put("articleList", articleList)
                .put("category", category)
                .put("site", site);

        //---------------------------------------custom properties start----------------------------------------------//


        articleExample.clear();
        articleExample.or().andArticleInHomepageEqualTo(true);
        List<Article> homePageArticleList = articleService.selectByExample(articleExample);
        addListToResult(result, homePageArticleList);

        //Add result to module
        model.addAttribute("result", result);

        //If skin = null, set default skin
        if (category.getCategorySkin() == null) {
            category.setCategorySkin(CmsConst.DEFAULT_SKIN);
        }

        //Return to the site's skin view, for example : default-category
        return category.getCategorySkin() + CmsConst.CATEGORY_SKIN_SUFFIX;
    }
}
