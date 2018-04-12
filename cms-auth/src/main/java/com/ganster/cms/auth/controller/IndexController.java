package com.ganster.cms.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @RequiresPermissions("super")
    @GetMapping("/index")
    public String index(){
        return "/login/admi.html";
    }
    @GetMapping("/403")
    public String error(){
        return "redirect:"+"/login/403.html";
    }
}
