package com.gangster.cms.admin.task.job;

import com.gangster.cms.admin.service.auth.TimedTaskService;
import com.gangster.cms.admin.task.BaseTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *    定时添加文章 Job
 */
public class AddTaskArticle implements BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(AddTaskArticle.class);

    @Autowired
    private TimedTaskService timedTaskService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer articleId = (Integer) jobExecutionContext.getJobDetail().getJobDataMap().get("id");
        Boolean addTaskArticle=timedTaskService.openArticleTask(articleId);
        if (!addTaskArticle){
            logger.info("添加失败");
        }
    }
}
