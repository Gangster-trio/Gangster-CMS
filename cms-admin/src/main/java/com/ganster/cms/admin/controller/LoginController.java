package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.dto.Message;
import com.ganster.cms.admin.web.CmsCommonBean;
import com.ganster.cms.core.constant.CmsConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public Message login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        logger.info("用户" + userName + "进行登录");
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Message message = new Message();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            message.setCode(100);
            message.setMsg("ok");
        } catch (Exception e) {
            message.setCode(120);
            message.setMsg("抱歉，信息错误");
            return message;
        }
        return message;
    }


}
