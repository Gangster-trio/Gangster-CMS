package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.dto.TaskArticle;
import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.auth.TimedTaskService;
import com.gangster.cms.admin.task.job.AddTaskArticle;
import com.gangster.cms.admin.util.DateUtil;
import com.gangster.cms.common.pojo.Article;
import com.gangster.cms.common.pojo.ArticleExample;
import com.github.pagehelper.PageInfo;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 定时发布文章Controller
 */
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;
    @Autowired
    private TimedTaskService timedTaskService;
    @Autowired
    private ArticleService articleService;


    @SystemControllerLog(description = "查找单篇文章")
    @GetMapping("/select/{id}")
    public TaskArticle selectArticle(@PathVariable("id") Integer id) {
        return timedTaskService.selectArticleById(id);
    }

    @SystemControllerLog(description = "查找所有文章")
    @GetMapping(value = "/list")
    public AjaxData listTaskArticle(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) {
        PageInfo<Article> pageInfo = timedTaskService.findTaskArticle(page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "添加定时文章")
    @PostMapping(value = "/addtask")
    public AjaxData addTaskArticle(
            @RequestBody TaskArticle taskArticle,
            @RequestParam(defaultValue = "root") String jobGroupName
    ) {
        try {
            //String 转化 Date
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setLenient(false);
            Date date = format.parse(taskArticle.getReleasetime());
            //将文章存入数据库中
            timedTaskService.addArticleTimedTask(taskArticle, date);
            //再次获得文章
            ArticleExample articleExample = new ArticleExample();
            articleExample.or().andArticleReleaseStatusEqualTo(false).andArticleTitleEqualTo(taskArticle.getArticleTitle());
            List<Article> article = articleService.selectByExample(articleExample);
            //获得Cron表达式
            String cronExpression = DateUtil.getCron(date);
            addTask(article.get(0), jobGroupName, cronExpression);
            return new AjaxData(0, "success", 0, null);
        } catch (Exception e) {
            logger.info("添加失败");
            return new AjaxData(1, "fail", 0, null);
        }
    }

    @SystemControllerLog(description = "更新定时文章")
    @PostMapping("/update/{id}")
    public MessageDto updateTaskArticle(@PathVariable("id") Integer id,
                                        @RequestBody TaskArticle taskArticle) {
        if (timedTaskService.updateTaskArticle(id, taskArticle)) {
            return MessageDto.success(null);
        } else return MessageDto.fail(1, "更新文章失败");
    }

    @SystemControllerLog(description = "删除单个定时发布文章")
    @GetMapping(value = "/deleteTask/{articleId}")
    public AjaxData deleteTaskArticle(
            @PathVariable("articleId") Integer articleId,
            @RequestParam(defaultValue = "root") String jobGroupName) throws Exception {
        Article article = articleService.selectByPrimaryKey(articleId);
        if (article == null) return new AjaxData(1, "fail", 0, null);
        else {
            String jobName = articleId + article.getArticleTitle();
            taskDelete(jobName, jobGroupName);
            articleService.deleteByPrimaryKey(articleId);
        }
        return new AjaxData(0, "success", 0, null);
    }

    @SystemControllerLog(description = "批量删除文章")
    @PostMapping("/batchDeleting")
    public AjaxData BatchDeleteArticle(String articleIdData) {
        if (timedTaskService.deleteArticles(articleIdData))
            return new AjaxData(0, "success", 0, null);
        else return new AjaxData(1, "fail", 0, null);
    }

    @SystemControllerLog(description = "暂停定时发布文章")
    @PostMapping(value = "/pausetask/{id}")
    public void pauseTask(
            @PathVariable("id") Integer id,
            @RequestParam(value = "root") String jobGroupName) {
        try {
            Article article = articleService.selectByPrimaryKey(id);
            String jobName = article.getArticleId() + article.getArticleTitle();
            taskPause(jobName, jobGroupName);
        } catch (SchedulerException e) {
            logger.info("暂停失败");
        }
    }

    public void addTask(Article article, String jobGroupName, String cronExpression) throws SchedulerException {
        scheduler.start();
        String jobName = article.getArticleId() + article.getArticleTitle();
        JobDetail jobDetail = JobBuilder.newJob(AddTaskArticle.class).withIdentity(jobName, jobGroupName).build();
        jobDetail.getJobDataMap().put("id", article.getArticleId());
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private void taskDelete(String jobName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
    }


    private void taskPause(String jobName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
    }
}
