package com.gangster.cms.admin.aspect;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gangster.cms.admin.annotation.CheckParam;
import com.gangster.cms.admin.annotation.CheckType;
import com.gangster.cms.admin.annotation.CmsPermission;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.util.PermissionUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.Category;
import com.gangster.cms.common.pojo.User;
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
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author Yoke
 * Created on 2018/4/14
 */
@Component
@Aspect
public class CheckPermissionAspect {


    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckPermissionAspect.class);

    @Pointcut("@annotation(com.gangster.cms.admin.annotation.CmsPermission)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void check(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
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


        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message= objectMapper.writeValueAsString(MessageDto.fail(2, "没有权限"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");


        switch (type) {
            case CATEGORY_WRITE:
                assert id != null;
                Integer categoryId = Integer.parseInt(id);
                Category category = categoryService.selectByPrimaryKey(categoryId);
                if (!PermissionUtil.permittedCategory(user.getUserId(), category.getCategorySiteId(), categoryId, CmsConst.PERMISSION_WRITE)) {
                    try {
                        response.getWriter().write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CATEGORY_READ:
                Integer categoryReadId = Integer.parseInt(id);
                Category readCategory = categoryService.selectByPrimaryKey(categoryReadId);
                if (!PermissionUtil.permittedCategory(user.getUserId(), readCategory.getCategorySiteId(), categoryReadId, CmsConst.PERMISSION_READ)) {
                    try {
                        response.getWriter().write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ARTICLE_WRITE:
                assert id != null;
                Integer articleId = Integer.parseInt(id);
                Article article = articleService.selectByPrimaryKey(articleId);
                if (!PermissionUtil.permittedCategory(user.getUserId(), article.getArticleSiteId(), article.getArticleCategoryId(), CmsConst.PERMISSION_WRITE)) {
                    try {
                        response.getWriter().write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ARTICLE_READ:
                assert id != null;
                Integer readArticleId = Integer.parseInt(id);
                Article readArticle = articleService.selectByPrimaryKey(readArticleId);
                if (!PermissionUtil.permittedCategory(user.getUserId(), readArticle.getArticleSiteId(),readArticle.getArticleCategoryId(), CmsConst.PERMISSION_READ)) {
                    try {
                        response.getWriter().write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ADMIN_CHECK:
                if (!user.getUserIsAdmin()) {
                    try {
                        response.getWriter().write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
        }

        LOGGER.info("type:" + type + "id" + id);

        if (!hasCountParam) {
            LOGGER.error("@CmsPermission 注解的方法中,参数必须有一个有 @CheckParam 注解，否则无法进行权限验证; 方法信息:{}", method);
        }
    }

}
