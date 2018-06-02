package com.gangster.cms.admin.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.service.LogService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.LogEntry;
import com.gangster.cms.common.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yoke
 * Created on 2018/4/11
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAspect.class);

    private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal beginTime");

    @Autowired
    private LogService logService;
/*

    @Autowired
    private UserService userService;
*/

    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.gangster.cms.admin.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    /**
     * Controller层的前置通知
     *
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        Date beginTime = new Date();
        BEGIN_TIME_THREAD_LOCAL.set(beginTime);
    }


    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute(CmsConst.CURRENT_USER);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SystemControllerLog annotation = method.getAnnotation(SystemControllerLog.class);
        if (null != user) {
            Map<String, Object> map = new HashMap<>();
            // 请求开始时间
            Date logStartTime = BEGIN_TIME_THREAD_LOCAL.get();
            long beginTime = BEGIN_TIME_THREAD_LOCAL.get().getTime();
            long endTime = System.currentTimeMillis();
            Long logElapsedTime = endTime - beginTime;
            map.put("path", request.getRequestURI());
            map.put("userName", user.getUserName());
            map.put("ip", request.getRemoteAddr());
            map.put("methodName", joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            map.put("time", logElapsedTime);
            try {
                map.put("description", annotation.description());
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("params", request.getParameterMap());
            LogEntry logEntry = new LogEntry();
            logEntry.setLogTime(logStartTime);
            logEntry.setLogType(CmsConst.LOG_ACCESS);
            logEntry.setLogLevel(CmsConst.LOG_INFO);
            try {
                logEntry.setLogInfo(new ObjectMapper().writeValueAsString(map));
            } catch (JsonProcessingException e) {
                LOGGER.error("异常信息: {}", e.getMessage());
                e.printStackTrace();
            }
            logService.insert(logEntry);
        }
    }
}
