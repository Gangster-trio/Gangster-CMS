package com.ganster.cms.admin.service;

import com.ganster.cms.core.pojo.LogEntry;
import com.ganster.cms.core.pojo.LogEntryExample;
import com.ganster.cms.core.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataWebService {
    @Autowired
    LogService logService;

    public PageInfo<LogEntry> getLog(int page, int limit, String logLevel) {
        LogEntryExample entryExample = new LogEntryExample();
        if (logLevel != null) {
            entryExample.or().andLogLevelEqualTo(logLevel);
        }
        return PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> {
                    logService.selectByExampleWithBLOBs(entryExample);
                });
    }
}
