package com.gangster.cms.web.service;

import com.gangster.cms.common.pojo.QuestionTopic;
import com.gangster.cms.common.pojo.QuestionTopicExample;
import com.gangster.cms.dao.mapper.QuestionAnswerMapper;
import com.gangster.cms.dao.mapper.QuestionPageMapper;
import com.gangster.cms.dao.mapper.QuestionTopicMapper;
import com.gangster.cms.web.cache.CmsCache;
import com.gangster.cms.web.cache.impl.LRUCache;
import com.gangster.cms.web.dto.ModelResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuestionWebService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionWebService.class);

    private final QuestionPageMapper questionPageMapper;

    private final QuestionAnswerMapper questionAnswerMapper;

    private final QuestionTopicMapper questionTopicMapper;

    private final CmsCache<Integer,ModelResult> cache = new LRUCache<>(128);

    public QuestionWebService(QuestionPageMapper questionPageMapper, QuestionAnswerMapper questionAnswerMapper, QuestionTopicMapper questionTopicMapper) {
        this.questionPageMapper = questionPageMapper;
        this.questionAnswerMapper = questionAnswerMapper;
        this.questionTopicMapper = questionTopicMapper;
    }

    public ModelResult getQuestionModel(Integer pageId) {
        if (cache.containsKey(pageId)){
            return cache.get(pageId);
        }

        QuestionPage page = questionPageMapper.selectByPrimaryKey(pageId);
        if (page == null) {
            return null;
        }

        //TODO
        return null;
    }
}
