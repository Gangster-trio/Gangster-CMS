package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.ArticleService;
import com.gangster.cms.admin.service.CategoryService;
import com.gangster.cms.admin.service.CountService;
import com.gangster.cms.admin.service.LogService;
import com.gangster.cms.admin.util.StringUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataWebService {
    private final
    LogService logService;

    private final
    CountService countService;

    private final
    ArticleService articleService;

    private final
    CategoryService categoryService;

    @Autowired
    public DataWebService(LogService logService, CountService countService, ArticleService articleService, CategoryService categoryService) {
        this.logService = logService;
        this.countService = countService;
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    public PageInfo<LogEntry> getLog(int page, int limit, String logType, String logLevel) {
        LogEntryExample entryExample = new LogEntryExample();
        if (logLevel != null) {
            entryExample.or().andLogLevelEqualTo(logLevel);
        }
        if (logType != null) {
            entryExample.or().andLogTypeEqualTo(logType);
        }
        entryExample.setOrderByClause("log_time desc");
        return PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> logService.selectByExampleWithBLOBs(entryExample));
    }

    // interval 需要是 CmsConst.COUNT_INTERVAL 的倍数
    public PageInfo<CountEntry> getCount(String type, String cid
            , Long start, Long end, Integer interval
            , Integer limit, Integer page) {

        if (interval < CmsConst.COUNT_INTERVAL) {
            return new PageInfo<>(Collections.emptyList(), 0);
        }

        if (end == null || end.equals(0L)) {
            end = System.currentTimeMillis();
        }

        CountEntryExample entryExample = new CountEntryExample();
        CountEntryExample.Criteria criteria = entryExample.or()
                .andCountTypeEqualTo(type)
                .andCountIntervalEqualTo(CmsConst.COUNT_INTERVAL);

        if (!StringUtil.isNullOrEmpty(cid)) {
            criteria.andCountCidEqualTo(cid);
        }
        criteria.andCountTimeBetween(start, end);
        PageInfo<CountEntry> countEntryList = PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> countService.selectByExample(entryExample));

        if (interval.equals(CmsConst.COUNT_INTERVAL)) {
            return countEntryList;
        }

        Map<Long, List<CountEntry>> map = countEntryList.getList()
                .stream()
                .collect(Collectors.groupingBy(
                        //取整分组
                        e -> ((e.getCountTime() - start) / interval) * interval + start
                        , Collectors.toList()));
        List<CountEntry> retList = new ArrayList<>();
        map.values().forEach(
                list -> list.stream()
                        .collect(Collectors.groupingBy(CountEntry::getCountCid))
                        .values()
                        .forEach(e -> e.stream()
                                .reduce((a, b) -> {
                                    CountEntry c = new CountEntry();
                                    c.setCountType(a.getCountType());
                                    c.setCountTime(a.getCountTime());
                                    c.setCountPv(a.getCountPv() + b.getCountPv());
                                    c.setCountInterval(a.getCountInterval() + b.getCountInterval());
                                    c.setCountCid(a.getCountCid());
                                    return c;
                                })
                                .ifPresent(retList::add))
        );

        return new PageInfo<>(retList);
    }

    private List<CountMapEntry> getMostView(String type, Long start, Long end, Integer interval, Integer limit, Integer page) {
        CountEntryExample entryExample = new CountEntryExample();
        CountEntryExample.Criteria criteria = entryExample.or();
        if (!StringUtil.isNullOrEmpty(type))
            criteria.andCountTypeEqualTo(type);
        if (start == null)
            start = 0L;
        if (end == null || end.equals(0L))
            criteria.andCountTimeGreaterThan(start);
        else
            criteria.andCountTimeBetween(start, end);
        PageInfo<CountEntry> info = PageHelper
                .startPage(page, limit)
                .doSelectPageInfo(() -> countService.selectByExample(entryExample));
        return info.getList().stream()
                .map(e -> new CountMapEntry(e.getCountCid(), e.getCountPv()))
                .collect(Collectors.groupingBy(CountMapEntry::getCid))
                .values()
                .stream()
                .map(
                        e -> e.stream().reduce((a, b) ->
                                new CountMapEntry(a.cid, a.pv + b.pv))
                                .orElseGet(() -> new CountMapEntry("", 0))
                )
                .sorted(Comparator.comparing(e -> e.pv))
                .collect(Collectors.toList());
    }


    public List getArticleMostView(Long start, Long end, Integer interval, Integer limit, Integer page) {
        List<CountMapEntry> list = getMostView("article", start, end, interval, limit, page);

        List<Integer> idList = list.stream()
                .map(e -> Integer.parseInt(e.cid))
                .collect(Collectors.toList());
        if (idList.isEmpty()) {
            return Collections.emptyList();
        }

        ArticleExample example = new ArticleExample();
        example.or().andArticleIdIn(idList);
        List<Article> articles = articleService.selectByExample(example);

        //文章ID与文章标题Map
        Map<Integer, String> map = new HashMap<>();
        articles.forEach(a -> map.put(a.getArticleId(), a.getArticleTitle()));

        //将id转换为文章标题
        list.forEach(e -> e.cid = map.get(Integer.parseInt(e.cid)));
        return list;
    }

    public List getCategoryMostView(Long start, Long end, Integer interval, Integer limit, Integer page) {
        List<CountMapEntry> list = getMostView("category", start, end, interval, limit, page);

        List<Integer> idList = list.stream()
                .map(e -> Integer.parseInt(e.cid))
                .collect(Collectors.toList());
        if (idList.isEmpty()) {
            return Collections.emptyList();
        }

        CategoryExample example = new CategoryExample();
        example.or().andCategoryIdIn(idList);
        List<Category> articles = categoryService.selectByExample(example);

        //栏目ID与栏目标题Map
        Map<Integer, String> map = new HashMap<>();
        articles.forEach(a -> map.put(a.getCategoryId(), a.getCategoryTitle()));

        //将id转换为栏目标题
        list.forEach(e -> e.cid = map.get(Integer.parseInt(e.cid)));
        return list;
    }

    class CountMapEntry {
        private String cid;
        private Integer pv;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public Integer getPv() {
            return pv;
        }

        public void setPv(Integer pv) {
            this.pv = pv;
        }

        CountMapEntry(String cid, Integer pv) {
            this.cid = cid;
            this.pv = pv;
        }
    }
}
