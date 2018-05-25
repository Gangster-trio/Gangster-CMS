package com.gangster.cms.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.admin.dto.MessageDto;
import com.gangster.cms.admin.service.web.SurveyWebService;
import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
import com.gangster.cms.common.pojo.SurveyPage;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@RestController
@RequestMapping("/survey/page")
public class SurveyPageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyPageController.class);
    private final SurveyWebService surveyPageWebService;

    @Autowired
    public SurveyPageController(SurveyWebService surveyPageWebService) {
        this.surveyPageWebService = surveyPageWebService;
    }

    @SystemControllerLog(description = "列出当前网站的素有问卷")
    @GetMapping("/list")
    public AjaxData list(@RequestParam Integer siteId, @RequestParam Integer page, @RequestParam Integer limit) {
        PageInfo<SurveyPage> pageInfo = surveyPageWebService.listSurveyPage(siteId, page, limit);
        if (null == pageInfo) {
            return new AjaxData(1, "failed", 0, null);
        }
        return new AjaxData(0, "success", pageInfo.getTotal(), pageInfo.getList());
    }

    @SystemControllerLog(description = "添加问卷")
    @Transactional
    @PostMapping("/add")
    public MessageDto add(HttpServletRequest request) {
        SurveyWithTopicWrapper wrapper = transformToPOJO(request);
        if (wrapper != null) {
            if (!surveyPageWebService.addSurveyPage(wrapper)) {
                return MessageDto.fail(1, "failed");
            }
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "failed");
    }


    @SystemControllerLog(description = "删除某个问卷")
    @Transactional
    @DeleteMapping("/delete/{id}")
    public MessageDto delete(@PathVariable("id") Integer id) {
        if (!surveyPageWebService.deleteSurveyPage(id)) {
            return MessageDto.fail(1, "failed");
        }
        return MessageDto.success(null);
    }

    @SystemControllerLog(description = "更新某个问卷")
    @Transactional
    @PostMapping("/update")
    public MessageDto update(HttpServletRequest request) {

        SurveyWithTopicWrapper wrapper = transformToPOJO(request);
        if (wrapper != null) {
            if (!surveyPageWebService.updateSurveyPage(wrapper)) {
                return MessageDto.fail(1, "failed");
            }
            return MessageDto.success(null);
        }
        return MessageDto.fail(1, "failed");
    }

    @SystemControllerLog(description = "查看某个问卷的详细信息")
    @GetMapping("/details/{id}")
    public MessageDto details(@PathVariable("id") Integer id) {
        return MessageDto.success(surveyPageWebService.detailsPage(id));
    }

    @SystemControllerLog(description = "问卷的统计信息")
    @GetMapping("/count/{id}")
    public MessageDto count(@PathVariable("id") Integer id) {
        return MessageDto.success(surveyPageWebService.countPage(id));
    }

    private SurveyWithTopicWrapper transformToPOJO(HttpServletRequest request) {
        BufferedReader br = null;
        SurveyWithTopicWrapper wrapper = null;
        try {
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            wrapper = JSONObject.parseObject(sb.toString(), SurveyWithTopicWrapper.class);
        } catch (IOException e) {
            LOGGER.error("转换出错,错误信息：{}", e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.error("关闭流出错");
                    e.printStackTrace();
                }
            }
        }
        return wrapper;
    }
}
