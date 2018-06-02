package com.gangster.cms.web.service;

import com.gangster.cms.common.dto.SearchResult;
import com.gangster.cms.common.pojo.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("es")
public interface SearchClient {

    @GetMapping("/search/article")
    SearchResult<Article> searchArticleAllSite(@RequestParam("keyword") String keyword
            , @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "limit", defaultValue = "10") int limit);

    @GetMapping("/search/article/{siteId}")
    SearchResult<Article> searchArticle(@PathVariable("siteId") Integer siteId
            , @RequestParam("keyword") String keyword
            , @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "limit", defaultValue = "10") int limit);
}
