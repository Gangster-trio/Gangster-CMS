package com.gangster.cms.admin.controller;


import com.gangster.cms.admin.service.web.DataWebService;
import com.gangster.cms.admin.dto.AjaxData;
import com.gangster.cms.common.pojo.CountEntry;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    private final
    DataWebService dataWebService;

    @Autowired
    public DataController(DataWebService dataWebService) {
        this.dataWebService = dataWebService;
    }

    @GetMapping("/log")
    public AjaxData getLog(@RequestParam(value = "log_type", required = false) String logType
            , @RequestParam(value = "log_level", required = false) String logLevel
            , @RequestParam(value = "page", defaultValue = "0") Integer page

            , @RequestParam(value = "limit", defaultValue = "10") Integer limit
    ) {
        PageInfo info = dataWebService.getLog(page, limit, logType, logLevel);
        return new AjaxData(0, "ok", info.getTotal(), info.getList());
    }

    @GetMapping("/count")
    public AjaxData getCount(@RequestParam(value = "type") String countType
            , @RequestParam(value = "cid", required = false) String countCid
            , @RequestParam(value = "start") Long start
            , @RequestParam(value = "end", required = false) Long end
            , @RequestParam("interval") Integer interval
            , @RequestParam(value = "limit", defaultValue = "10") Integer limit
            , @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        PageInfo<CountEntry> info = dataWebService.getCount(countType, countCid, start, end, interval, limit, page);
        return new AjaxData(0, "ok", info.getTotal(), info.getList());
    }

    @GetMapping("/most_view")
    public AjaxData mostView(@RequestParam("type") String type
            , @RequestParam(value = "start", defaultValue = "0") Long start
            , @RequestParam(value = "end", defaultValue = "0") Long end
            , @RequestParam("interval") Integer interval
            , @RequestParam(value = "limit", defaultValue = "10") Integer limit
            , @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List list;
        switch (type) {
            case "article":
                list = dataWebService.getArticleMostView(start, end, interval, limit, page);
                break;
            case "category":
                list = dataWebService.getCategoryMostView(start, end, interval, limit, page);
                break;
            default:
                return new AjaxData(1, "type参数错误", 0, null);
        }
        return new AjaxData(0, "ok", list.size(), list);
    }

    public void areoCount(Long start,Long end){

    }
}
