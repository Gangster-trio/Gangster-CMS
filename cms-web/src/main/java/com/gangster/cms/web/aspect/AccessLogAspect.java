package com.gangster.cms.web.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.LogEntry;
import com.gangster.cms.dao.mapper.LogEntryMapper;
import com.gangster.cms.web.util.IPUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类负责对{@link com.gangster.cms.web.annotation.AccessLogger}横切，记录访问日志
 */
@Component
@Aspect
public class AccessLogAspect {

    private final Logger logger = LoggerFactory.getLogger("Access log");

    private static final ThreadLocal<Long> timeCount = new ThreadLocal<>();

    private final
    LogEntryMapper logEntryMapper;

    @Autowired
    public AccessLogAspect(LogEntryMapper logEntryMapper) {
        this.logEntryMapper = logEntryMapper;
    }

    @Pointcut("@annotation(com.gangster.cms.web.annotation.AccessLogger)")
    public void loggerService() {
    }

    @Before("loggerService()")
    public void doBeforeAdvice() {
        timeCount.set(Calendar.getInstance().getTimeInMillis());
    }

    @After("loggerService()")
    public void doAfterAdvice() {
        Long time = Calendar.getInstance().getTimeInMillis() - timeCount.get();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String path = request.getRequestURI();
        String ip = getIpAddress(request);

        Map<String, Object> map = new HashMap<>();
        Map<String, String[]> paramMap = request.getParameterMap();
        map.put("path", path);
        map.put("ip", ip);
        map.put("param", paramMap);
        map.put("addr", IPUtil.getAddr(ip));
        map.put("time", time);

        LogEntry logEntry = new LogEntry();
        logEntry.setLogTime(Calendar.getInstance().getTime());
        logEntry.setLogType(CmsConst.LOG_ACCESS);
        logEntry.setLogLevel(CmsConst.LOG_INFO);
        try {
            logEntry.setLogInfo(new ObjectMapper().writeValueAsString(map));
            logger.info(logEntry.getLogInfo());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }

        logEntryMapper.insert(logEntry);
    }


    /**
     * 获取真实IP地址
     *
     * @param request 请求对象
     * @return 字符串形式的IP地址
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
