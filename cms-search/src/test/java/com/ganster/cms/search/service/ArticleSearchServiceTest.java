package com.ganster.cms.search.service;

import com.ganster.cms.search.CmsSearchApplication;
import com.ganster.cms.search.model.ArticleModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CmsSearchApplication.class)
public class ArticleSearchServiceTest {
    @Autowired
    ArticleSearchService searchService;

    @Test
    public void saveTest(){
        ArticleModel article = new ArticleModel();
        article.setArticleId(1);
        article.setArticleTitle("at");
        article.setArticleContent("today apple orange spring");
        article = searchService.save(article);
        System.out.println(article);
    }
}
