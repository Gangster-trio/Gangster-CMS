package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.dto.MessageDto;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.pojo.UserExample;
import com.ganster.cms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public MessageDto<Object> login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpServletRequest request) {
        logger.info("用户" + userName + "进行登录");
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(userName).andUserPasswordEqualTo(password);
        User user = userService.selectByExample(userExample).get(0);
        if (user != null) {
            logger.info("用户:" + userName + "登录成功");
            request.getSession().setAttribute(CmsConst.CURRENT_USER, user);
            return MessageDto.success(null);
        } else {
            return MessageDto.fail(1, "登录失败");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(CmsConst.CURRENT_USER);
        try {
            response.sendRedirect("/login/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
