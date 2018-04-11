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
    private final
    LogService logService;

    @Autowired
    public DataWebService(LogService logService) {
        this.logService = logService;
    }

    public PageInfo<LogEntry> getLog(int page, int limit, String logType, String logLevel) {
        LogEntryExample entryExample = new LogEntryExample();
        if (logLevel != null) {
            entryExample.or().andLogLevelEqualTo(logLevel);
        }
        if (logType != null) {
            entryExample.or().andLogTypeEqualTo(logType);
        }
        return PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> {
                    logService.selectByExampleWithBLOBs(entryExample);
                });
    }

}
