package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.UserService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;
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
import java.util.List;


@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public MessageDto<Object> login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password, HttpServletRequest request) {
        logger.info("用户" + userName + "进行登录");
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(userName).andUserPasswordEqualTo(password);
        List<User> users = userService.selectByExample(userExample);
        if (!users.isEmpty()) {
            User user = userService.selectByExample(userExample).get(0);
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
