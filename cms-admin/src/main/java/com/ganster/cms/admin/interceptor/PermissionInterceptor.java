package com.ganster.cms.admin.interceptor;

import com.ganster.cms.admin.web.CmsCommonBean;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.Article;
import com.ganster.cms.core.pojo.Category;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.CategoryService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yoke
 * Created on 2018/4/13
 */
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionInterceptor.class);

    private static final String SYSTEMATICS_ARTICLE = "article";

    private static final String SYSTEMATICS_CATEGORY = "category";

    private static final String NO_PERMISSION_VIEW = "/module/NoPermission.html";
    private static final String LOGIN_VIEW = "/login";
    private static final String ADD_OPERATION = "add";
    private static final String LIST_OPERATION = "list";

    private static final String UPDATE_OPERATION = "update";
    private static final String DELETE_OPERATION = "delete";
    private static final String CHECK_OPERATION = "check";
    private String systematics;
    private String operation;
    private Integer id;

    @Autowired
    PermissionService permissionService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CmsCommonBean cmsCommonBean;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (LOGIN_VIEW.equals(uri)) {
            return true;
        }
        String[] strings = StringUtils.split(uri, "/");
        List list = Arrays.asList(strings);
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("id");
        if (userId == null) {
            return true;
        }
        User user = cmsCommonBean.getUser();
        // 三个参数，代表进行一系列更新删除操作
        if (list.size() == 3) {
            systematics = strings[0];
            operation = strings[1];
            if (operation.equals(UPDATE_OPERATION) || operation.equals(DELETE_OPERATION)) {
                id = Integer.valueOf(strings[2]);
                // 如果操作的文章
                if (SYSTEMATICS_ARTICLE.equals(systematics)) {
                    Article article = articleService.selectByPrimaryKey(id);
                    if (user.getUserIsAdmin() || permissionService.hasCategoryPermission(userId, article.getArticleSiteId(), article.getArticleId(), CmsConst.PERMISSION_WRITE)) {
                        return true;
                    } else {
                        response.sendRedirect(NO_PERMISSION_VIEW);
                    }
                } else if (SYSTEMATICS_CATEGORY.equals(systematics)) {
                    Category category = categoryService.selectByPrimaryKey(id);
                    if (user.getUserIsAdmin() || permissionService.hasCategoryPermission(userId, category.getCategorySiteId(), id, CmsConst.PERMISSION_WRITE)) {
                        return true;
                    } else {
                        response.sendRedirect(NO_PERMISSION_VIEW);
                    }
                }
                return true;
                // 判断操作是否是审核工作
            } else if (operation.equals(CHECK_OPERATION) || list.contains(CHECK_OPERATION)) {
                return user.getUserIsAdmin();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

}
