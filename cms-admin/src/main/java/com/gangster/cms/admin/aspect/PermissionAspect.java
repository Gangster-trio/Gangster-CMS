package com.gangster.cms.admin.aspect;

import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.annotation.SiteId;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.ModuleMapper;
import com.gangster.cms.dao.mapper.PermissionMapper;
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
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Aspect
public class PermissionAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionAspect.class);

    private final
    PermissionMapper permissionMapper;

    private final
    ModuleMapper moduleMapper;

    private ConcurrentHashMap<String, Module> moduleCache = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Integer, List<Permission>> permissionCache = new ConcurrentHashMap<>();

    @Autowired
    public PermissionAspect(PermissionMapper permissionMapper, ModuleMapper moduleMapper) {
        this.permissionMapper = permissionMapper;
        this.moduleMapper = moduleMapper;
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
            response.sendRedirect("401");
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

        Module module = moduleCache.get(moduleName);
        if (module == null) {
            ModuleExample moduleExample = new ModuleExample();
            moduleExample.or().andModuleNameEqualTo(moduleName);
            List<Module> modules = moduleMapper.selectByExample(moduleExample);
            if (modules.isEmpty()) {
                LOGGER.error("未找到名称为" + moduleName + "的模块");
                return point.proceed();
            }
            module = modules.get(0);
            moduleCache.put(moduleName, module);
        }

        List<Permission> permissionList = permissionCache.get(user.getUserId());
        if (permissionList == null) {
            PermissionExample permissionExample = new PermissionExample();
            permissionExample.or().andUserIdEqualTo(user.getUserId());
            permissionList = permissionMapper.selectByExample(permissionExample);
            permissionCache.put(user.getUserId(), permissionList);
        }
        boolean hasPermission = false;
        for (Permission permission : permissionList) {
            if (permission.getModuleId().equals(module.getModuleId())) {
                hasPermission = true;
            }
        }
        if (hasPermission) {
            return point.proceed();
        } else {
            // TODO: 5/31/18 添加未授权页面
            response.sendRedirect("401");
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
