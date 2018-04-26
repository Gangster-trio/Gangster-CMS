package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.ArticleDTO;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.auth.TimedTaskService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/timetask")
public class TimedTaskController {

    private static final Logger logger = LoggerFactory.getLogger(TimedTaskController.class);
    @Autowired
    private TimedTaskService timedTaskService;

    @SystemControllerLog(description = "定时添加文章")
    @GetMapping("/save")
    public MessageDto timeTaskSaveArticle(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestBody ArticleDTO articleDTO) {
        if (!timedTaskService.addArticleTimedTask(articleDTO)) {
            logger.error("添加文章失败");
            return MessageDto.fail(1, "添加文章失败");
        }
        return MessageDto.success(null);
    }
}
