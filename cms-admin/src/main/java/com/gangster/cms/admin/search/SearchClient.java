package com.gangster.cms.admin.search;

import com.gangster.cms.common.pojo.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("es")
public interface SearchClient {

    @PostMapping("article")
    Article save(Article article);
}
