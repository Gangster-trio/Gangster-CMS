package com.ganster.cms.auth.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/login")
    public com.ganster.cms.auth.dto.Message login(Model model, HttpServletRequest request){
        String username=request.getParameter("userName");
        String password=request.getParameter("password");
        logger.info("用户"+username+"进行登录");
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
//        token.setRememberMe(true);
        com.ganster.cms.auth.dto.Message message=new com.ganster.cms.auth.dto.Message();
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(token);
            message.setCode(100);
            message.setMsg("ok");
        }catch (Exception e) {
            message.setCode(120);
            message.setMsg("抱歉，信息错误");
            return message;
        }
        return message;
    }


}
