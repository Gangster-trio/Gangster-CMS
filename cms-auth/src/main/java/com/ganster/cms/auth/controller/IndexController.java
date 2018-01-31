package com.ganster.cms.auth.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "/login/admin.html";
    }
    @GetMapping("/403")
    public String error(){
        return "/login/403.html";
    }
}
