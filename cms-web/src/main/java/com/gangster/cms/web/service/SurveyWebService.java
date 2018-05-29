package com.gangster.cms.web.service;

import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
import com.gangster.cms.common.dto.TopicWithOptionWrapper;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.SurveyOptionMapper;
import com.gangster.cms.dao.mapper.SurveyPageMapper;
import com.gangster.cms.dao.mapper.SurveyTopicMapper;
import com.gangster.cms.web.cache.CmsCache;
import com.gangster.cms.web.cache.impl.LRUCache;
import com.gangster.cms.web.dto.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SurveyWebService {
    private static final Logger logger = LoggerFactory.getLogger(SurveyWebService.class);
    private final CmsCache<Integer,ModelResult> cache = new LRUCache<>(128);

    private final SurveyPageMapper pageMapper;
    private final SurveyOptionMapper optionMapper;
    private final SurveyTopicMapper topicMapper;

    public SurveyWebService(SurveyPageMapper pageMapper, SurveyOptionMapper optionMapper, SurveyTopicMapper topicMapper) {
        this.pageMapper = pageMapper;
        this.optionMapper = optionMapper;
        this.topicMapper = topicMapper;
    }

    public ModelResult getQuestionModel(Integer pageId) {
        if (cache.containsKey(pageId)){
            return cache.get(pageId);
        }

        SurveyWithTopicWrapper surveyPage = getSurveyPage(pageId);
        ModelResult result = new ModelResult();
        result.put("page",surveyPage);

        cache.put(pageId,result);
        return result;
    }

    public SurveyWithTopicWrapper getSurveyPage(Integer pageId) {
        SurveyPage page = pageMapper.selectByPrimaryKey(pageId);
        //问卷调查页面和题目
        SurveyWithTopicWrapper survey = new SurveyWithTopicWrapper(page, null);
        if (page != null) {
            SurveyTopicExample topicExample = new SurveyTopicExample();
            topicExample.or().andTopicPageIdEqualTo(pageId);
            //所有题目
            List<SurveyTopic> topicList = topicMapper.selectByExample(topicExample);
            //所有题目ID
            List<Integer> topicIdList = topicList.stream().map(SurveyTopic::getTopicId).collect(Collectors.toList());
            if (!topicIdList.isEmpty()) {
                SurveyOptionExample optionExample = new SurveyOptionExample();
                optionExample.or().andTopicIdIn(topicIdList);
                //所有选项
                List<SurveyOption> optionList = optionMapper.selectByExample(optionExample);
                //每个题目对应的选项
                Map<Integer, List<SurveyOption>> optionMap = optionList.stream().collect(Collectors.groupingBy(SurveyOption::getTopicId));
                //所有题目（包括选项）
                List<TopicWithOptionWrapper> topicWithOptionWrapperList = new ArrayList<>();
                topicList.forEach(e -> topicWithOptionWrapperList.add(new TopicWithOptionWrapper(e, optionMap.get(e.getTopicId()))));
                survey.setTopicList(topicWithOptionWrapperList);
            }
        }
        return survey;
    }

    public void submitCheck(List<Integer> idList){
        idList.forEach(id->{
            SurveyOption option = optionMapper.selectByPrimaryKey(id);
            int count = option.getOptionCount();
            option.setOptionCount(count+  1);
            optionMapper.updateByPrimaryKeySelective(option);
        });
    }

    public void submitText(Map<String ,String > map){
        map.forEach((topicId,content)->{
            SurveyOption option = new SurveyOption();
            option.setOptionContent(content);
            option.setTopicId(Integer.parseInt(topicId));
            optionMapper.insertSelective(option);
        });
    }

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void flushCache() {
        cache.clear();
        logger.info("refresh survey cache");
    }
}
