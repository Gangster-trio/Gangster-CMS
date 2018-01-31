package com.ganster.cms.auth.controller;


import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.pojo.UserExample;
import com.ganster.cms.core.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/operationUser")
public class OperationUserController {
    private static final Logger logger = LoggerFactory.getLogger(OperationUserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @RequiresPermissions("supper")
    public void addUser(@RequestParam("User") User user) {
        userService.insert(user);
    }

    @PostMapping("/update")
    public int updateUser(@RequestParam("User") User user) {
        return userService.updateByPrimaryKey(user);
    }

    @GetMapping("/delect")
    @RequiresPermissions("supper")
    public void delectUser(@RequestParam("UserId") Integer userId) {
        userService.deleteByPrimaryKey(userId);
    }

    @GetMapping
    @RequiresPermissions("supper")
    public List<User> findUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIsNotNull();
        return userService.selectByExample(userExample);
    }
}
