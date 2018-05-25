package com.gangster.cms.admin.controller;

import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.CategoryExample;
import com.gangster.cms.dao.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Yoke
 * Created on 2018/5/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/list")
    public Flux<Category> list() {
        return Flux.fromIterable(categoryMapper.selectByExample(new CategoryExample()));
    }

    @GetMapping("/detail/{id}")
    public Mono<Category> details(@PathVariable("id") Integer id) {
        return Mono.fromSupplier(() ->
                categoryMapper.selectByPrimaryKey(id)
        );
    }


    @DeleteMapping("/delete")
    public Mono<Void> delete(String filename) {
        return Mono.fromRunnable(() ->
                categoryMapper.deleteByPrimaryKey(1)
        );
    }



}
