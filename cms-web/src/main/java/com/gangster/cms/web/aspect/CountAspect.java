package com.gangster.cms.web.aspect;

import com.gangster.cms.web.annotation.AccessCount;
import com.gangster.cms.web.annotation.CountParam;
import com.gangster.cms.web.service.CountScheduleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class CountAspect {
    private final
    CountScheduleService countScheduleService;

    private static final Logger logger = LoggerFactory.getLogger(CountAspect.class);

    @Autowired
    public CountAspect(CountScheduleService countScheduleService) {
        this.countScheduleService = countScheduleService;
    }

    @Pointcut("@annotation(com.gangster.cms.web.annotation.AccessCount)")
    public void countPoint() {
    }

    @After("countPoint()")
    public void count(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        AccessCount annotation = method.getDeclaredAnnotation(AccessCount.class);
        String type = annotation.value().toString();
        int paramCount = method.getParameterCount();
        boolean hasCountParam = false;
        Object[] args = point.getArgs();
        for (int i = 0; i < paramCount; i++) {
            //Spring框架中的一个Helper类
            MethodParameter parameter = new MethodParameter(method, i);
            if (parameter.hasParameterAnnotation(CountParam.class)) {
                String id = String.valueOf(args[i]);
                countScheduleService.addPV(type, id);
                hasCountParam = true;
                break;
            }
        }
        if (!hasCountParam) {
            logger.error("@AccessCount 注解的方法中,参数必须有一个有 @CountParam 注解，否则无法进行统计; 方法信息:{}", method);
        }
    }
}
