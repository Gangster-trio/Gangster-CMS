package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.task.job.AddTaskArticle;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @PostMapping(value = "/addtask")
    public AjaxData addtask(
            @RequestBody  ArticleDTO articleDTO,
            @RequestParam(defaultValue = "root") String jobGroupName,
            @RequestParam(defaultValue = "0 * 3 6 5 ?") String cronExpression
    ) {
        try {
            addTask(articleDTO, jobGroupName, cronExpression);
            logger.info("success+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return new AjaxData(0, "success", 0, null);
        } catch (Exception e) {
            logger.info("添加失败");
            return new AjaxData(1, "fail", 0, null);
        }
    }

    @PostMapping(value = "/pausetask")
    public void pausetask(@RequestParam(value = "jobGroupName") String jobGroupName) {
        try {
            taskPause("PauseTaskArticle", jobGroupName);
        } catch (SchedulerException e) {
            logger.info("暂停失败");
        }
    }

    @PostMapping(value = "/deletetask")
    public void deletetask(@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        taskdelete("DeleteTaskArticle", jobGroupName);
    }

    private void taskdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    private void taskPause(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    private void addTask(ArticleDTO articleDTO, String jobGroupName, String cronExpression) throws Exception {
        scheduler.start();
        JobDetail jobDetail = JobBuilder.newJob(AddTaskArticle.class).withIdentity("AddTaskArticle", jobGroupName).build();
        jobDetail.getJobDataMap().put("articleDTO", articleDTO);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("AddTaskArticle", jobGroupName).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

}
