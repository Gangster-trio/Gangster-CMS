package com.ganster.cms.auth.controller;

import com.ganster.cms.auth.config.shiro.UserShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ganster.cms.auth.dto.Message;
import javax.servlet.http.HttpServletRequest;



@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(UserShiroRealm.class);
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
        }catch (Exception e) {
            return message;
        }
        message.setCode(110);
        message.setData("121");
        return message;
    }
    @RequestMapping("/main")
    public String index(){
        return   "admin";
    }
    @RequestMapping("/404")
    public String error(){
        return "404";
    }

}
