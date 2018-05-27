package com.gangster.cms.admin.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseTask extends Job{
    @Override
     void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
