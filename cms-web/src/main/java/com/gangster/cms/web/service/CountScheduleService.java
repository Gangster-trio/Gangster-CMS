package com.gangster.cms.web.service;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.CountEntry;
import com.gangster.cms.dao.mapper.CountEntryMapper;
import com.gangster.cms.web.cache.CmsCache;
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

    /**
     * {@link CmsConst#COUNT_INTERVAL} 必须一样
     */
    private final static long ONE_MINUTE = 60 * 1000;
    private final static long countInterval = ONE_MINUTE * 10;

    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> PVCount = new ConcurrentHashMap<>();

    private final
    CountEntryMapper countEntryMapper;

    @Autowired
    public CountScheduleService(CountEntryMapper countEntryMapper) {
        this.countEntryMapper = countEntryMapper;
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
        entry.setCountTime(Calendar.getInstance().getTimeInMillis());
        entry.setCountPv(count.get());
        entry.setCountInterval((int) countInterval);
        countEntryMapper.insert(entry);
        logger.debug("flush count entry:{}", entry);
    }
}
