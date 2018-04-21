package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.search.SearchClient;
import com.gangster.cms.common.dto.SearchResult;
import com.gangster.cms.common.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Autowired
    private final SearchClient searchClient;

    public SearchController(SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @GetMapping("/search/article/{id}")
    public SearchResult<Article> search(@PathVariable("id") Integer sid
            , @RequestParam String keyword
            , @RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        return searchClient.searchArticle(sid, keyword, page, limit);
    }

    @GetMapping("/search/article")
    public SearchResult<Article> searchAll(@RequestParam String keyword
            , @RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        return searchClient.searchArticleAllSite(keyword, page, limit);
    }
}
