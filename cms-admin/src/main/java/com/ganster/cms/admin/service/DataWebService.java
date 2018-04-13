package com.ganster.cms.admin.service;

import com.ganster.cms.core.pojo.CountEntry;
import com.ganster.cms.core.pojo.CountEntryExample;
import com.ganster.cms.core.pojo.LogEntry;
import com.ganster.cms.core.pojo.LogEntryExample;
import com.ganster.cms.core.service.CountService;
import com.ganster.cms.core.service.LogService;
import com.ganster.cms.core.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataWebService {
    private final
    LogService logService;

    private final
    CountService countService;

    @Autowired
    public DataWebService(LogService logService, CountService countService) {
        this.logService = logService;
        this.countService = countService;
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
                .doSelectPageInfo(() -> logService.selectByExampleWithBLOBs(entryExample));
    }

    public PageInfo<CountEntry> getCount(String type, String cid, Long start, Long end, Integer interval, Integer limit, Integer page) {
        CountEntryExample entryExample = new CountEntryExample();
        CountEntryExample.Criteria criteria = entryExample.or()
                .andCountTypeEqualTo(type)
                .andCountIntervalEqualTo(interval);
        if (!StringUtil.isNullOrEmpty(cid)) {
            criteria.andCountCidEqualTo(cid);
        }
        if (end == null || end.equals(0L)) {
            criteria.andCountTimeGreaterThan(start);
        } else {
            criteria.andCountTimeBetween(start, end);
        }
        return PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> countService.selectByExample(entryExample));
    }

    public List getMostView(String type, Long start, Long end, Integer interval, Integer limit, Integer page) {
        CountEntryExample entryExample = new CountEntryExample();
        CountEntryExample.Criteria criteria = entryExample.or();
        if (!StringUtil.isNullOrEmpty(type))
            criteria.andCountTypeEqualTo(type);
        if (start == null || start.equals(0L))
            start = 0L;
        if (end == null || end.equals(0L))
            criteria.andCountTimeGreaterThan(start);
        else
            criteria.andCountTimeBetween(start, end);
        PageInfo<CountEntry> info = PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> countService.selectByExample(entryExample));
        Map<String, Integer> map = new HashMap<>();
        info.getList().forEach(entry -> {
            String cid = entry.getCountCid();
            Integer t = map.get(cid);
            if (t == null) {
                t = 0;
            }
            t += entry.getCountPv();
            map.put(cid, t);
        });
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        return list;
    }
}
