package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.ArticleMapper;
import com.ganster.cms.core.dao.pojo.Article;
import com.ganster.cms.core.dao.pojo.ArticleExample;
import com.ganster.cms.core.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article, ArticleExample> implements ArticleService {
}
