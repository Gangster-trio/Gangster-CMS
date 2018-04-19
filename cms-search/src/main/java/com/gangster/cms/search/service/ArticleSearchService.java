package com.gangster.cms.search.service;

import com.gangster.cms.search.model.ArticleModel;
import com.gangster.cms.search.repository.ArticleSearchRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ArticleSearchService {
    private final Logger logger = LoggerFactory.getLogger(ArticleSearchService.class);
    private final ArticleSearchRepository searchRepository;
    private final String[] SEARCH_FIELDS = {"articleContent", "articleTitle", "articleAuthor", "articleDesc"};

    public ArticleSearchService(ArticleSearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public ArticleModel save(ArticleModel article) {
        return searchRepository.save(article);
    }

    public void saveAll(Iterable<ArticleModel> articleModels){
        searchRepository.saveAll(articleModels);
    }

    public Page<ArticleModel> search(String keyWord, int page, int limit, Integer siteId) {
        HighlightBuilder.Field[] fields = new HighlightBuilder.Field[SEARCH_FIELDS.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new HighlightBuilder.Field(SEARCH_FIELDS[i]);
        }

        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.functionScoreQuery(QueryBuilders
                        .multiMatchQuery(keyWord, SEARCH_FIELDS)))
                .withHighlightFields(fields)
                .withSort(SortBuilders.scoreSort());
        if (siteId != null) {
            query.withQuery(QueryBuilders.termQuery("articleSiteId", siteId));
        }
        logger.info("Search {};page {};limit {};siteId {}", keyWord, page, limit,siteId);
        return searchRepository.search(query.build().getQuery(), PageRequest.of(page, limit));
    }

}
