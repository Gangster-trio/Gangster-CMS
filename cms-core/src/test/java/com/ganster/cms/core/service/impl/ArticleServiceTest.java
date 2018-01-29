package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.CoreApplication;
import com.ganster.cms.core.dao.mapper.ArticleMapper;
import com.ganster.cms.core.dao.pojo.Article;
import com.ganster.cms.core.dao.pojo.ArticleExample;
import com.ganster.cms.core.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class ArticleServiceTest {
    @Autowired
    ArticleService articleService;


}
