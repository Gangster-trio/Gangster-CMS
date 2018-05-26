package com.gangster.cms.admin.listener;

import com.gangster.cms.admin.controller.TaskController;
import com.gangster.cms.admin.dto.TaskArticle;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.util.DateUtil;
import com.gangster.cms.common.pojo.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.*;

@WebListener
public class QuartzJobListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(QuartzJobListener.class);

    @Autowired
    private ArticleService articleService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        TaskController taskController = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(TaskController.class);
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Article> articles = articleService.selectArticleByReleaseStatus(false);
        for (Article article : articles) {
            Date date = article.getArticleReleaseTime();
            String cronExpression = DateUtil.getCron(date);
            Map<String, Object> map = new HashMap<>();
            map.put("article", new TaskArticle(article));
            map.put("jobGroupName", "root");
            map.put("cronExpression", cronExpression);
            mapList.add(map);
        }
        for (Map<String, Object> map : mapList) {
            taskController.addTask((TaskArticle) map.get("article"), (String) map.get("jobGroupName"));
        }
        logger.info("定时任务添加成功");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
