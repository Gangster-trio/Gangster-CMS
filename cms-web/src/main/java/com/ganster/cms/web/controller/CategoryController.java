package com.ganster.cms.web.controller;

import com.ganster.cms.core.CmsConst;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/category/")
public class CategoryController {
    private final CategoryService categoryService;
    private final ArticleService articleService;

    @Autowired
    public CategoryController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @RequestMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.selectByPrimaryKey(id);

        if (category==null){
            return "404";
        }

        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleCategoryIdEqualTo(id);
        List<Article> articles = articleService.selectByExample(articleExample);

        model.addAttribute("articleList", articles);
        model.addAttribute("category", category);

        return category.getCategorySkin() + CmsConst.CATEGORY_SKIN_SUFFIX;
    }
}
