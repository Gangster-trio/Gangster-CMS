package com.gangster.cms.search.api;

import com.gangster.cms.common.dto.SearchResult;
import com.gangster.cms.search.model.ArticleModel;
import com.gangster.cms.search.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleApi {
    private final
    ArticleSearchService searchService;

    @Autowired
    public ArticleApi(ArticleSearchService searchService) {
        this.searchService = searchService;
    }

//    @PostMapping("/article")
//    public ArticleModel save(@RequestBody ArticleModel articleModel) {
//        return searchService.save(articleModel);
//    }
//
//    @PostMapping("/article/save_all")
//    public void saveAll(@RequestBody List<ArticleModel> articleModelList) {
//        searchService.saveAll(articleModelList);
//    }

    @GetMapping("/search/article")
    public SearchResult<ArticleModel> searchAllSite(@RequestParam String keyword
            , @RequestParam(defaultValue = "0") Integer page
            , @RequestParam(defaultValue = "10") Integer limit) {
        Page<ArticleModel> modelPage = searchService.search(keyword, page, limit, null);
        return new SearchResult<>((int) modelPage.getTotalElements(), modelPage.getContent());
    }

    @GetMapping("/search/article/{siteId}")
    public SearchResult<ArticleModel> searchArticle(@PathVariable("siteId") Integer siteId
            , @RequestParam String keyword
            , @RequestParam(value = "page", defaultValue = "0") int page
            , @RequestParam(value = "limit", defaultValue = "10") int limit) {
        Page<ArticleModel> modelPage = searchService.search(keyword, page, limit, siteId);
        return new SearchResult<>((int) modelPage.getTotalElements(), modelPage.getContent());
    }
}
