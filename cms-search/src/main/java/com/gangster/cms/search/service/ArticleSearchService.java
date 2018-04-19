package com.gangster.cms.search.service;

import com.gangster.cms.search.model.ArticleModel;
import com.gangster.cms.search.repository.ArticleSearchRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {
    private final Logger logger = LoggerFactory.getLogger(ArticleSearchService.class);
    private final ArticleSearchRepository searchRepository;

    public ArticleSearchService(ArticleSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public ArticleModel save(ArticleModel article) {
        return searchRepository.save(article);
    }

    public void saveAll(Iterable<ArticleModel> articleModels){
        searchRepository.saveAll(articleModels);
    }

    public Page<ArticleModel> search(String keyWord, int page, int limit) {

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.functionScoreQuery(QueryBuilders
                        .multiMatchQuery(keyWord, "articleContent", "articleTitle", "articleAuthor", "articleDesc")))
                .build();
        logger.info("Search {};page {};limit {}", keyWord, page, limit);
        return searchRepository.search(query);
    }

}
