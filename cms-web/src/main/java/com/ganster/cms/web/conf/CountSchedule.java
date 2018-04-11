package com.ganster.cms.web.conf;

import com.ganster.cms.core.pojo.CountEntry;
import com.ganster.cms.core.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CountSchedule {
    private final static long ONE_MINUTE = 60 * 1000;
    private final static long ONE_HOUR = 60 * ONE_MINUTE;
    private final static long ONE_DAY = 24 * ONE_HOUR;

    private static ConcurrentHashMap<Integer, AtomicInteger> articlePVCount = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, AtomicInteger> categoryPVCount = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, AtomicInteger> sitePVCount = new ConcurrentHashMap<>();


    private final
    CountService countService;

    @Autowired
    public CountSchedule(CountService countService) {
        this.countService = countService;
    }

    @Scheduled(fixedDelay = ONE_HOUR)
    public void hourCount() {
        articlePVCount.forEach((id, count) -> flushCount(id, count, "article"));
        categoryPVCount.forEach((id, count) -> flushCount(id, count, "category"));
        sitePVCount.forEach((id, count) -> flushCount(id, count, "site"));
    }

    private void flushCount(Integer id, AtomicInteger count, String category) {
        CountEntry entry = new CountEntry();
        entry.setCountType(category);
        entry.setCountCid(id);
        entry.setCountTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        entry.setCountPv(count.get());
        count.set(0);
        countService.insert(entry);
    }
}
