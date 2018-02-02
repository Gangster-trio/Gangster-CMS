package com.ganster.cms.auth.controller;


import com.ganster.cms.auth.dto.AjaxData;
import com.ganster.cms.auth.dto.Message;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.exception.UserNotFoundException;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.pojo.UserExample;
import com.ganster.cms.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public void addUser(@PathVariable("User") User user) {
        userService.insert(user);
    }

    @PostMapping(value = "/update/{userid}")
    @ResponseBody
    public Message updateUser(@PathVariable("userid") Integer userid, @RequestBody User user) {
        Message message = new Message();
        int updateNumber;
        if (userService.selectByPrimaryKey(userid) != null) {
            updateNumber = userService.updateByPrimaryKeySelective(user);
            User user1=userService.selectByPrimaryKey(userid);
            message.setData(updateNumber);
            message.setCode(0);
            message.setMsg("success");
        }
        return message;
    }

    @GetMapping("/delete/{UserId}")
    public Message deleteUser(@PathVariable("UserId") Integer userId) {
        Message message = new Message();
        int delectNumber;
        if (userService.selectByPrimaryKey(userId) != null) {
            logger.info("++++++++++++++++" + userId + "++++++++++++++++++++++");
            try {
                userService.deleteUser(userId);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            } catch (GroupNotFountException e) {
                logger.info("++++++++++++++++++++++++++++++++++用户组为空++++++++++++++++++++++++++++++++++");
            }
            message.setMsg("success");
            message.setCode(0);
            message.setData(1);
        }
        return message;
    }

    @GetMapping("/find")
    @ResponseBody
    public AjaxData findUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdIsNotNull();
        List<User> userList = userService.selectByExample(userExample);
        AjaxData ajaxData = new AjaxData();
        int count = 0;
        for (User i : userList) {
            count++;
        }
        ajaxData.setCount(count);
        ajaxData.setData((ArrayList) userList);
        return ajaxData;
    }

    @GetMapping("/find/{UserId}")
    @ResponseBody
    public User fingUserById(@PathVariable("UserId") Integer userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        List<User> userList = userService.selectByExample(userExample);
        User user = userList.get(0);
        return user;
    }
}
