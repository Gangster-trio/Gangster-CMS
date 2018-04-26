//package com.gangster.cms.admin.aspect;
//
//import com.gangster.cms.admin.service.auth.test;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.Map;
//
//@Aspect
//@Component
//public class SpringTaskAspect {
//    private static final Logger logger = LoggerFactory.getLogger(SpringTaskAspect.class);
//
//    @Pointcut(value = "execution(* com.gangster.cms.admin.service.auth.Task.task())")
//    public void controllerAspect() {
//    }
//
//    @Before(value = "controllerAspect()")
//    public void doBefore() {
//        test test = new test();
//        try {
//            Method method = test.getClass().getDeclaredMethod("print");
//            Scheduled scheduled=method.getAnnotation(Scheduled.class);
//            InvocationHandler invocationHandler= Proxy.getInvocationHandler(scheduled);
//            Field field=invocationHandler.getClass().getDeclaredField("memberValues");
//            field.setAccessible(true);
//            Map memberValues= (Map) field.get(invocationHandler);
//            memberValues.put("cron","*/5 * * * * ?");
//            String cron=scheduled.cron();
//            logger.info("========="+cron);
//        } catch (NoSuchMethodException e) {
//            logger.error("发生错误");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//}
