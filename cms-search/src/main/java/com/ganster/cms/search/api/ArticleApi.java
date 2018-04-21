package com.ganster.cms.search.api;

import com.ganster.cms.search.model.ArticleModel;
import com.ganster.cms.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleApi {
    @Autowired
    ArticleSearchService searchService;

    @PostMapping("/article")
    public ArticleModel save(@RequestBody ArticleModel articleModel) {
        return searchService.save(articleModel);
    }
}
