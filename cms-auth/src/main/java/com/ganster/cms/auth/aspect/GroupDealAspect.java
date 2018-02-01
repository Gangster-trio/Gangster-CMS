//package com.ganster.cms.auth.aspect;
//
//import com.ganster.cms.core.pojo.Group;
//import com.ganster.cms.core.pojo.GroupExample;
//import com.ganster.cms.core.service.GroupService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//
//@Aspect
//@Component
//public class GroupDealAspect {
//    private static final Logger logger = LoggerFactory.getLogger(GroupDealAspect.class);
//
//    @Autowired
//    private GroupService groupService;
//
//    private String groupName = "group";
//
//    @Pointcut("execution(* com.ganster.cms.auth.controller.AllotGroupController..*.*(..))")
//    public void judgeGroup() {
//    }
//
//    @Before("judgeGroup()")
//    public String before(JoinPoint joinPoint) {
//        Subject subject = SecurityUtils.getSubject();
//        GroupExample groupExample = new GroupExample();
//        List<Group> groupList = groupService.selectByExample(groupExample);
//        for (Group i : groupList) {
//            if (!subject.hasRole("group" + i.getGroupId())) {
//                logger.info("+++++++++++++++++++++++++++++++++++++++该用户不属于栏目组+++++++++++++++++++++++++++++++++++++");
////                    response.sendRedirect(request.getContextPath() + "/login/403.html");
//             return "/login/403.html";
//            }
//        }
//        logger.info("+++++++++++++++++++++++++++++++++++++++++该用户属于栏目组++++++++++++++++++++++++++++++++++++++++++");
//        return null;
//    }
//}
