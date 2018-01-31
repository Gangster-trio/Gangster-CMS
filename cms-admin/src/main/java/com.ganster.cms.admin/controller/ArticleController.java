package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.common.AjaxData;
import com.ganster.cms.admin.common.Message;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.ArticleExample;
import com.ganster.cms.core.service.ArticleService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    @ResponseBody
    public AjaxData list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        ArticleExample articleExample = new ArticleExample();
        List<Article> list = articleService.selectByExample(articleExample);
        System.out.println(list.get(0));
        if (page != null && limit != null) {
            PageInfo<Article> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) articleService.selectByExample(articleExample));
        } else {
            PageInfo<Article> pageInfo = PageHelper.startPage(0, 0).doSelectPageInfo(() -> articleService.selectByExample(articleExample));
            return super.buildAjaxData(0, "success", pageInfo.getSize(), (ArrayList) articleService.selectByExample(articleExample));
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public Message save(@RequestBody Article article) {
        if (article != null) {
            int count = articleService.insert(article);
            return super.buildMessage(0, "success", count);
        } else {
            return super.buildMessage(1, "false", 0);
        }
    }
}
