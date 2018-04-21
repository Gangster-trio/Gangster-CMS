package com.ganster.cms.search.service;

import com.ganster.cms.search.model.ArticleModel;
import com.ganster.cms.search.repository.ArticleSearchRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {
    private final ArticleSearchRepository searchRepository;

    public ArticleSearchService(ArticleSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public ArticleModel save(ArticleModel article) {
        return searchRepository.save(article);
    }

    public Page<ArticleModel> searchContent(String keyWord){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(keyWord)).build();
        return searchRepository.search(searchQuery);
    }

}
