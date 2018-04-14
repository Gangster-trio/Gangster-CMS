/*package com.ganster.cms.admin.controller;


import com.ganster.cms.admin.dto.AjaxData;
import com.ganster.cms.admin.service.DataWebService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            , @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
//        PageInfo info = dataWebService.getLog(page, limit, logLevel);
        return new AjaxData(0, "ok", info.getTotal(), info.getList());
    }
}*/
