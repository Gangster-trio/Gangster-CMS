package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.search.SearchClient;
import com.gangster.cms.common.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    SearchClient searchClient;

    @PostMapping("/test/save")
    public Article save(Article article){
        return searchClient.save(article);
    }
}
