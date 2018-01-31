package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.CoreApplication;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
public class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceTest.class);

    @Test
    @Transactional
    public void PageHelperTest() {
        final String TEST_AUTHOR = "PageHelperTest.-@#$%^&*&^%";
        //insert Data
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setArticleAuthor(TEST_AUTHOR);
            articles.add(article);
        }
        for (Article article : articles) {
            articleService.insert(article);
        }

        ArticleExample articleExample = new ArticleExample();
        articleExample.or().andArticleAuthorEqualTo(TEST_AUTHOR);
        PageHelper.startPage(1,2);
        articles = articleService.selectByExample(articleExample);
        PageInfo<Article> pageInfo= new PageInfo<>(articles);

//        PageInfo page = PageHelper.startPage(1,5).doSelectPageInfo(() -> articleService.selectByExample(articleExample));

        LOGGER.info(pageInfo.toString());
        for (Article article:articles){
            LOGGER.info(article.toString());
        }
        Assert.assertEquals(articles.size(),2);
//        articleService.deleteByExample(articleExample);
    }

    @Test
    @Transactional
    public void generatorAutoGetKeyTest(){
        Article article = new Article();
        article.setArticleAuthor("generatorAutoGetKeyTest");
        articleService.insert(article);
        LOGGER.info(article.toString());
        Assert.assertNotNull(article.getArticleId());
        Assert.assertNotEquals(article.getArticleId().intValue(),0);
    }
}
