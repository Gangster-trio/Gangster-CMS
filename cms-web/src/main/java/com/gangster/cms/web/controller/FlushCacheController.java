package com.gangster.cms.web.controller;

import com.gangster.cms.web.service.ArticleWebService;
import com.gangster.cms.web.service.CategoryWebService;
import com.gangster.cms.web.service.SiteWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlushCacheController {
    @Autowired
    ArticleWebService articleWebService;
    @Autowired
    CategoryWebService categoryWebService;
    @Autowired
    SiteWebService siteWebService;

    @GetMapping("/test/flush")
    public void flush(){
        articleWebService.flushCache();
        categoryWebService.flushCache();
        siteWebService.flushCache();
    }
}
