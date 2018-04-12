package com.ganster.cms.web.service;

import com.ganster.cms.core.pojo.CountEntry;
import com.ganster.cms.core.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CountScheduleService {
    private final static Logger logger = LoggerFactory.getLogger(CountScheduleService.class);

    private final static long ONE_MINUTE = 60 * 1000;
    private final static long countInterval = ONE_MINUTE * 10;

    private static ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> PVCount = new ConcurrentHashMap<>();

    private final
    CountService countService;

    @Autowired
    public CountScheduleService(CountService countService) {
        this.countService = countService;
    }

    //统计粒度10分钟
    @Scheduled(fixedDelay = countInterval)
    public void count() {
        logger.info("flushing count info to DB...");
        PVCount.forEach((type, map)
                -> map.forEach((id, count)
                -> flushCountToDB(id, count, type))
        );
        PVCount.clear();
        logger.info("flush count info succeed!");
    }

    public int addPV(String type, String id) {
        if (!PVCount.containsKey(type)) {
            PVCount.put(type, new ConcurrentHashMap<>());
        }
        ConcurrentHashMap<String, AtomicInteger> map = PVCount.get(type);
        if (!map.containsKey(id)) {
            map.put(id, new AtomicInteger());
        }
        return map.get(id).incrementAndGet();
    }

    private void flushCountToDB(String id, AtomicInteger count, String type) {
        CountEntry entry = new CountEntry();
        entry.setCountType(type);
        entry.setCountCid(id);
        entry.setCountTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        entry.setCountPv(count.get());
        entry.setCountInterval((int) countInterval);
        countService.insert(entry);
        logger.debug("flush count entry:{}", entry);
    }
}
