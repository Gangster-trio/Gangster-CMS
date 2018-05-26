package com.gangster.cms.web.controller;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.web.annotation.AccessCount;
import com.gangster.cms.web.annotation.AccessLogger;
import com.gangster.cms.web.annotation.CountParam;
import com.gangster.cms.web.annotation.CountType;
import com.gangster.cms.web.dto.ModelResult;
import com.gangster.cms.web.service.ArticleWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/article/")
public class ArticleController {

    private final ArticleWebService webService;

    @Autowired
    public ArticleController(ArticleWebService webService) {
        this.webService = webService;
    }

    @AccessLogger()
    @AccessCount(value = CountType.ARTICLE)
    @RequestMapping("{id}")
    public String show(@CountParam @PathVariable("id") Integer id, Model model) {

        ModelResult result = webService.getArticleModel(id);

        if (result == null) {
            return "404";
        }

        model.addAttribute("result", result);

        Article article = (Article) result.get("article");

        //If skin = null, put default skin
        if (article.getArticleSkin() == null) {
            article.setArticleSkin(CmsConst.DEFAULT_SKIN);
        }

        //Return to the site's skin view, for example : default-article
        return article.getArticleSkin() + CmsConst.ARTICLE_SKIN_SUFFIX;
    }
}
