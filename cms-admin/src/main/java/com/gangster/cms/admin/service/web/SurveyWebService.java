package com.gangster.cms.admin.service.web;

import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.dto.SurveyWithTopicWrapper;
import com.gangster.cms.common.dto.TopicWithOptionWrapper;
import com.gangster.cms.common.pojo.*;
import com.gangster.cms.dao.mapper.SurveyOptionMapper;
import com.gangster.cms.dao.mapper.SurveyPageMapper;
import com.gangster.cms.dao.mapper.SurveyTopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yoke
 * Created on 2018/4/27
 */
@Service
public class SurveyWebService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyWebService.class);

    @Autowired
    private SurveyPageMapper surveyPageMapper;
    @Autowired
    private SurveyTopicMapper surveyTopicMapper;
    @Autowired
    private SurveyOptionMapper surveyOptionMapper;

    public PageInfo<SurveyPage> listSurveyPage(Integer siteId, Integer page, Integer limit) {
        SurveyPageExample surveyPageExample = new SurveyPageExample();
        surveyPageExample.or().andPageSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> surveyPageMapper.selectByExample(surveyPageExample));
    }

    public boolean addSurveyPage(SurveyWithTopicWrapper wrapper) {
        return addSurveyPageWithTopicAndOptions(wrapper.getPage(), wrapper.getTopicList());
    }

    public boolean deleteSurveyPage(Integer surveyPageId) {
        return deleteSurveyPageWithTopicAndOptions(surveyPageId);
    }

    public boolean updateSurveyPage(SurveyWithTopicWrapper wrapper) {
        SurveyPage surveyPage = wrapper.getPage();

        // 已删除的题，会以A只有pageId的形式传过来
        List<TopicWithOptionWrapper> topicWithOptionWrappers = wrapper.getTopicList().stream().filter(param -> null != param.getTopic().getTopicQuestion()).collect(Collectors.toList());
        try {
            surveyPageMapper.updateByPrimaryKey(surveyPage);
            SurveyTopicExample surveyTopicExample = new SurveyTopicExample();
            surveyTopicExample.or().andTopicPageIdEqualTo(surveyPage.getPageId());
            List<Integer> updateIds = topicWithOptionWrappers.stream().map(e -> e.getTopic().getTopicId()).collect(Collectors.toList());
            List<Integer> toDeleteIds = surveyTopicMapper.selectByExample(surveyTopicExample).stream().filter(param -> !updateIds.contains(param.getTopicId())).map(SurveyTopic::getTopicId).collect(Collectors.toList());
            deleteSurveyTopicsAndOptions(toDeleteIds);

//            topicWithOptionWrappers.forEach(e -> {
            for (TopicWithOptionWrapper e : topicWithOptionWrappers) {
                SurveyTopic surveyTopic = e.getTopic();
                if (null == surveyTopic.getTopicId()) {
                    surveyTopic.setTopicPageId(surveyPage.getPageId());
                    surveyTopicMapper.insert(surveyTopic);
                    e.getOptionList().forEach(t -> {
                        t.setTopicId(surveyTopic.getTopicId());
                        t.setOptionCount(0);
                        surveyOptionMapper.insert(t);
                    });
                } else {
                    surveyTopicMapper.updateByPrimaryKeySelective(surveyTopic);
                    SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
                    surveyOptionExample.or().andTopicIdEqualTo(surveyTopic.getTopicId());
                    List<Integer> updateOptionIds = e.getOptionList().stream().map(SurveyOption::getOptionId).collect(Collectors.toList());
                    List<Integer> toDeleteOptionsIds = surveyOptionMapper.selectByExample(surveyOptionExample).stream().filter(param -> !updateOptionIds.contains(param.getOptionId())).map(SurveyOption::getOptionId).collect(Collectors.toList());
                    deleteSurveyOptions(toDeleteOptionsIds);

                    e.getOptionList().forEach(t -> {
                        t.setOptionCount(0);
                        if (null == t.getOptionId()) {
                            surveyOptionMapper.insert(t);
                        } else {
                            surveyOptionMapper.updateByPrimaryKeySelective(t);
                        }
                    });
                }
            }
//            });
        } catch (Exception e) {
            LOGGER.error("更新问卷失败，失败信息：{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public SurveyWithTopicWrapper detailsPage(Integer surveyPageId) {
        return detailsPageMethod(true, surveyPageId);
    }

    public SurveyWithTopicWrapper countPage(Integer surveyPageId) {
        return detailsPageMethod(false, surveyPageId);
    }


    // -----------------------------------------------SurveyPage方法------------------------------------------------------------------

    private boolean addSurveyPageWithTopicAndOptions(SurveyPage surveyPage, List<TopicWithOptionWrapper> topicWithOptionWrappers) {
        try {
            surveyPageMapper.insert(surveyPage);
            topicWithOptionWrappers.forEach(e -> {
                SurveyTopic surveyTopic = e.getTopic();
                surveyTopic.setTopicPageId(surveyPage.getPageId());
                surveyTopicMapper.insert(surveyTopic);
                e.getOptionList().forEach(t -> {
                    t.setTopicId(surveyTopic.getTopicId());
                    t.setOptionCount(0);
                    surveyOptionMapper.insert(t);
                });
            });
        } catch (Exception e) {
            LOGGER.error("添加问卷时插入失败,错误信息 {}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean deleteSurveyPageWithTopicAndOptions(Integer surveyPageId) {
        SurveyPage surveyPage = surveyPageMapper.selectByPrimaryKey(surveyPageId);
        try {
            if (surveyPage != null) {
                SurveyTopicExample surveyTopicExample = new SurveyTopicExample();
                surveyTopicExample.or().andTopicPageIdEqualTo(surveyPageId);
                List<Integer> surveyTopicIds = surveyTopicMapper.selectByExample(surveyTopicExample).stream().map(SurveyTopic::getTopicId).collect(Collectors.toList());
                if (surveyTopicIds.size() != 0) {
                    SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
                    surveyOptionExample.or().andTopicIdIn(surveyTopicIds);
                    List<SurveyOption> options = surveyOptionMapper.selectByExample(surveyOptionExample);
                    surveyOptionMapper.deleteByExample(surveyOptionExample);
                    surveyTopicMapper.deleteByExample(surveyTopicExample);
                }
                surveyPageMapper.deleteByPrimaryKey(surveyPageId);
            }
        } catch (Exception e) {
            LOGGER.error("删除问卷时出错,错误信息：{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private SurveyWithTopicWrapper detailsPageMethod(boolean showAll, Integer surveyPageId) {
        SurveyPage surveyPage = surveyPageMapper.selectByPrimaryKey(surveyPageId);
        SurveyTopicExample surveyTopicExample = new SurveyTopicExample();
        if (showAll) {
            surveyTopicExample.or().andTopicPageIdEqualTo(surveyPageId);
        } else {
            surveyTopicExample.or().andTopicPageIdEqualTo(surveyPageId).andTopicTypeNotEqualTo(CmsConst.topic_type_3);
        }
        SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
        List<TopicWithOptionWrapper> topicWithOptionWrappers = surveyTopicMapper.selectByExample(surveyTopicExample).stream().map(e -> {
            surveyOptionExample.clear();
            surveyOptionExample.or().andTopicIdEqualTo(e.getTopicId());
            return new TopicWithOptionWrapper(e, surveyOptionMapper.selectByExample(surveyOptionExample));
        }).collect(Collectors.toList());
        return new SurveyWithTopicWrapper(surveyPage, topicWithOptionWrappers);
    }

    //---------------------------------------------SurveyTopic部分-----------------------------------------------------------------

    public boolean deleteSurveyTopic(Integer surveyTopicId) {
        try {
            SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
            surveyOptionExample.or().andTopicIdEqualTo(surveyTopicId);
            surveyOptionMapper.deleteByExample(surveyOptionExample);
            surveyTopicMapper.deleteByPrimaryKey(surveyTopicId);
        } catch (Exception e) {
            LOGGER.error("删除问题时出错，错误信息：{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void deleteSurveyTopicsAndOptions(List<Integer> surveyTopicIds) {
        assert surveyTopicIds != null;
        if (surveyTopicIds.size() != 0) {
            try {
                SurveyTopicExample surveyTopicExample = new SurveyTopicExample();
                surveyTopicExample.or().andTopicIdIn(surveyTopicIds);

                SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
                surveyTopicMapper.selectByExample(surveyTopicExample).forEach(e -> {
                    surveyOptionExample.or().andTopicIdEqualTo(e.getTopicId());
                    surveyOptionMapper.deleteByExample(surveyOptionExample);
                    surveyOptionExample.clear();
                });
                surveyTopicMapper.deleteByExample(surveyTopicExample);
            } catch (Exception e) {
                LOGGER.error("删除问题发生错误,错误信息:", e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //-------------------------------------------SurveyOption部分------------------------------------------------------------------------

    public boolean deleteSurveyOption(Integer surveyOptionId) {
        return surveyOptionMapper.deleteByPrimaryKey(surveyOptionId) == 1;
    }


    private void deleteSurveyOptions(List<Integer> surveyOptionIds) {
        if (surveyOptionIds.size() != 0) {
            SurveyOptionExample surveyOptionExample = new SurveyOptionExample();
            surveyOptionExample.or().andOptionIdIn(surveyOptionIds);
            surveyOptionMapper.deleteByExample(surveyOptionExample);
        }
    }


}
