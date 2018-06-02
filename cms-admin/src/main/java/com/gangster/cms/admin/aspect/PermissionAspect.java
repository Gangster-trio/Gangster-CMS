package com.gangster.cms.admin.aspect;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Component
@Aspect
public class PermissionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionAspect.class);

    private final
    PermissionService permissionService;


    @Autowired
    public PermissionAspect(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Pointcut("@annotation(com.gangster.cms.admin.annotation.CmsPermission)")
    public void permissionPoint() {

    }

    /**
     * 使用Around配合{@link ProceedingJoinPoint}横切要注意返回值
     * {@link ProceedingJoinPoint#proceed()}返回原方法的返回值
     */
    @Around("permissionPoint()")
    public Object doBeforeAdvice(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        HttpServletResponse response =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getResponse();
        User user = (User) request.getSession().getAttribute(CmsConst.CURRENT_USER);
        if (user == null) {
            LOGGER.error("session中用户不存在");
            response.sendRedirect("/401");
            return null;
        }
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        CmsPermission annotation = method.getAnnotation(CmsPermission.class);
        String moduleName = annotation.moduleName();
        Integer siteId = getParamSiteId(point);

        if (siteId == null) {
            LOGGER.error("@CmsPermission注解的方法中需要有参数被@SiteId注解.");
            return point.proceed();
        }

        if (permissionService.hasPermission(user.getUserId(), siteId, moduleName)) {
            return point.proceed();
        } else {
            // TODO: 5/31/18 添加未授权页面
            response.sendRedirect("/401");
            return null;
        }
    }

    private Integer getParamSiteId(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            MethodParameter parameter = new MethodParameter(method, i);
            if (parameter.hasParameterAnnotation(SiteId.class)) {
                return (Integer) args[i];
            }
        }
        return null;
    }
}
