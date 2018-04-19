package com.gangster.cms.search.api;

import com.gangster.cms.search.model.ArticleModel;
import com.gangster.cms.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApi {
    private final
    ArticleSearchService searchService;

    @Autowired
    public ArticleApi(ArticleSearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/article")
    public ArticleModel save(@RequestBody ArticleModel articleModel) {
        return searchService.save(articleModel);
    }

    @PostMapping("/article/save_all")
    public void saveAll(@RequestBody List<ArticleModel> articleModelList) {
        searchService.saveAll(articleModelList);
    }

    @GetMapping("article/search")
    public Page<ArticleModel> search(@RequestParam String keyword
            , @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        return searchService.search(keyword, page, limit);
    }
}
