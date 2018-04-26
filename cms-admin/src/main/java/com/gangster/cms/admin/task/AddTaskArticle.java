package com.gangster.cms.admin.task;

import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.service.auth.TimedTaskService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AddTaskArticle implements BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(AddTaskArticle.class);

    private TimedTaskService timedTaskService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ArticleDTO articleDTO = (ArticleDTO) jobExecutionContext.getJobDetail().getJobDataMap().get("articleDTO");
        Boolean addTaskArticle=timedTaskService.addArticleTimedTask(articleDTO);
        if (!addTaskArticle){
            logger.info("添加失败");
        }
    }
}
