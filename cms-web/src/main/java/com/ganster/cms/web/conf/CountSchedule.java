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

    private static ConcurrentHashMap<Integer, AtomicInteger> articlePVHourCount = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, AtomicInteger> categoryPVHourCount = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Integer, AtomicInteger> sitePVHourCount = new ConcurrentHashMap<>();

    private final
    CountService countService;

    @Autowired
    public CountSchedule(CountService countService) {
        this.countService = countService;
    }

    @Scheduled(fixedDelay = ONE_HOUR)
    public void hourCount() {
        articlePVHourCount.forEach((id, count) -> flushCountToDB(id, count, "article"));
        categoryPVHourCount.forEach((id, count) -> flushCountToDB(id, count, "category"));
        sitePVHourCount.forEach((id, count) -> flushCountToDB(id, count, "site"));

        articlePVHourCount.clear();
        categoryPVHourCount.clear();
        sitePVHourCount.clear();
    }

    public int addArticlePV(int id) {
        if (!articlePVHourCount.containsKey(id)) {
            articlePVHourCount.put(id, new AtomicInteger(0));
        }
        return articlePVHourCount.get(id).incrementAndGet();
    }

    public int addCategoryPV(int id) {
        if (!categoryPVHourCount.containsKey(id)) {
            categoryPVHourCount.put(id, new AtomicInteger(0));
        }
        return categoryPVHourCount.get(id).incrementAndGet();
    }

    public int addSitePV(int id) {
        if (!sitePVHourCount.containsKey(id)) {
            sitePVHourCount.put(id, new AtomicInteger(0));
        }
        return sitePVHourCount.get(id).incrementAndGet();
    }

//    public int getArticlePV(int id) {
//        return articleCountCache.get(id)
//                .addAndGet(articlePVHourCount
//                        .getOrDefault(id, new AtomicInteger(0))
//                        .get());
//    }
//
//    public int getCategoryPV(int id) {
//        return categoryCountCache.get(id)
//                .addAndGet(categoryPVHourCount
//                        .getOrDefault(id, new AtomicInteger(0))
//                        .get());
//    }
//
//    public int getSitePV(int id) {
//        return siteCountCache.get(id)
//                .addAndGet(sitePVHourCount
//                        .getOrDefault(id, new AtomicInteger(0))
//                        .get());
//    }
//
//    private int getArticlePV(int id) {
//        if (!articleCountCache.containsKey(id)) {
//
//        }
//    }

//    private void delCountCache() {
//        articleCountCache.clear();
//        categoryCountCache.clear();
//        siteCountCache.clear();
//    }

    private void flushCountToDB(Integer id, AtomicInteger count, String type) {
        CountEntry entry = new CountEntry();
        entry.setCountType(type);
        entry.setCountCid(id);
        entry.setCountTime(String.valueOf(Calendar.getInstance().getTimeInMillis()));
        entry.setCountPv(count.get());

        countService.insert(entry);
    }
}
