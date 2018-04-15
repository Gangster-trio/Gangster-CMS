package com.ganster.cms.search.service;

import com.ganster.cms.search.model.ArticleModel;
import com.ganster.cms.search.repository.ArticleSearchRepository;
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

}
