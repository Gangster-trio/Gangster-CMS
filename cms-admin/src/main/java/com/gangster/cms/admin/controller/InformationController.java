package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.auth.UserConcreteService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 *  用户的个人信息 controller
 */
@RestController
@RequestMapping("/information")
public class InformationController {

    public static final Logger logger = LoggerFactory.getLogger(InformationController.class);

    @Autowired
    private UserConcreteService userConcreteService;

    @ResponseBody
    @GetMapping
    public MessageDto getInformation() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        User user = (User) request.getSession().getAttribute(CmsConst.CURRENT_USER);
        logger.info(user.getUserName());
        if (userConcreteService.findSingleUser(user.getUserId()) != null) {
            return MessageDto.success(user);
        } else return MessageDto.fail(1, "查找用户信息失败");
    }

}
