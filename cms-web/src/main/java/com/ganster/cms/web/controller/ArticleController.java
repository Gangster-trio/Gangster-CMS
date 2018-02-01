package com.ganster.cms.web.controller;

import com.ganster.cms.core.CmsConst;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.Tag;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/article/")
public class ArticleController {

    private final ArticleService articleService;

    private final CategoryService categoryService;

    private final TagService tagService;

    @Autowired
    public ArticleController(ArticleService articleService, CategoryService categoryService, TagService tagService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @RequestMapping("{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Article article = articleService.selectByPrimaryKey(id);

        if (article == null){
            return "404";
        }

        Category category = categoryService.selectByPrimaryKey(article.getArticleCategoryId());
        List<Tag> tagList = tagService.selectByArticleId(id);

        model.addAttribute("category", category);
        model.addAttribute("article", article);
        model.addAttribute("tagList", tagList);

        return article.getArticleSkin() + CmsConst.ARTICLE_SKIN_SUFFIX;
    }
}
