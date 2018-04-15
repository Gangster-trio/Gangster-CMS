package com.ganster.cms.admin.aspect;

import com.ganster.cms.admin.annotation.CheckParam;
import com.ganster.cms.admin.annotation.CheckType;
import com.ganster.cms.admin.annotation.CmsPermission;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Yoke
 * Created on 2018/4/14
 */
@Component
@Aspect
public class CheckPermissionAspect {


    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private CategoryService categoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckPermissionAspect.class);

    @Pointcut("@annotation(com.ganster.cms.admin.annotation.CmsPermission)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void check(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CmsPermission annotation = method.getDeclaredAnnotation(CmsPermission.class);

        CheckType type = annotation.checkType();
        int paramCount = method.getParameterCount();
        boolean hasCountParam = false;
        Object[] args = joinPoint.getArgs();
        String id = null;
        for (int i = 0; i < paramCount; i++) {
            //Spring框架中的一个Helper类
            MethodParameter parameter = new MethodParameter(method, i);
            if (parameter.hasParameterAnnotation(CheckParam.class)) {
                id = String.valueOf(args[i]);
                hasCountParam = true;
                break;
            }
        }
        User user = (User) request.getSession().getAttribute(CmsConst.CURRENT_USER);
        switch (type) {
            case CATEGORY_WRITE:

        }
        /*if (type.equals(CheckType.ARTICLE_READ)) {

        } else if (type.equals(CheckType.ARTICLE_WRITE)) {
            assert id != null;
            Integer articleId = Integer.valueOf(id);
            Article article = articleService.selectByPrimaryKey(articleId);
            if (!user.getUserIsAdmin() || !PermissionUtil.permittedCategory(user.getUserId(), article.getArticleId(), article.getArticleCategoryId(), CmsConst.PERMISSION_WRITE)) {
                LOGGER.info("当前用户{}没有权限访问:{}", user.getUserName(), request.getRequestURI());
                handleNoPermission(response);
            }
        } else if (type.equals(CheckType.CATEGORY_READ)) {

        } else if (type.equals(CheckType.CATEGORY_WRITE)) {
            assert id != null;
            Integer categoryId = Integer.valueOf(id);
            Category category = categoryService.selectByPrimaryKey(categoryId);
            if (!user.getUserIsAdmin() || !PermissionUtil.permittedCategory(user.getUserId(), category.getCategorySiteId(), categoryId, CmsConst.PERMISSION_WRITE)) {
                LOGGER.info("当前用户{}没有权限访问{}", user.getUserName(), request.getRequestURI());
                handleNoPermission(response);
            }
        }*/

        LOGGER.info("type:" + type + "id" + id);

        if (!hasCountParam) {
            LOGGER.error("@CmsPermission 注解的方法中,参数必须有一个有 @CheckParam 注解，否则无法进行权限验证; 方法信息:{}", method);
        }
    }

    public void handleNoPermission(HttpServletResponse response) {
        String s = "Sorry you have no permission";
        try {
            response.getWriter().write(s);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
