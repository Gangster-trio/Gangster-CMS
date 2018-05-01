package com.gangster.cms.dao.mapper;

import java.util.List;

import com.gangster.cms.common.pojo.SurveyTopic;
import com.gangster.cms.common.pojo.SurveyTopicExample;
import org.apache.ibatis.annotations.Param;

public interface SurveyTopicMapper {
    long countByExample(SurveyTopicExample example);

    int deleteByExample(SurveyTopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(SurveyTopic record);

    int insertSelective(SurveyTopic record);

    List<SurveyTopic> selectByExample(SurveyTopicExample example);

    SurveyTopic selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") SurveyTopic record, @Param("example") SurveyTopicExample example);

    int updateByExample(@Param("record") SurveyTopic record, @Param("example") SurveyTopicExample example);

    int updateByPrimaryKeySelective(SurveyTopic record);

    int updateByPrimaryKey(SurveyTopic record);
}