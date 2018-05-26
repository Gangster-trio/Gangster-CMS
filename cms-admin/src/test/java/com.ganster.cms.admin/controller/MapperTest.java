package com.ganster.cms.admin.controller;

import com.gangster.cms.admin.CmsAdminApplication;
import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.dao.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Yoke
 * Created on 2018/4/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CmsAdminApplication.class )

public class MapperTest {
    @Autowired
    ArticleMapper mapper;
    @Test
    public void test(){
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setCategoryName("sdsd");
        articleDTO.setArticleAuthor("TEST");
        mapper.insert(articleDTO);
    }
}
