package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.QuestionTopic;
import com.gangster.cms.common.pojo.QuestionTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionTopicMapper {
    long countByExample(QuestionTopicExample example);

    int deleteByExample(QuestionTopicExample example);

    int deleteByPrimaryKey(Integer topicId);

    int insert(QuestionTopic record);

    int insertSelective(QuestionTopic record);

    List<QuestionTopic> selectByExample(QuestionTopicExample example);

    QuestionTopic selectByPrimaryKey(Integer topicId);

    int updateByExampleSelective(@Param("record") QuestionTopic record, @Param("example") QuestionTopicExample example);

    int updateByExample(@Param("record") QuestionTopic record, @Param("example") QuestionTopicExample example);

    int updateByPrimaryKeySelective(QuestionTopic record);

    int updateByPrimaryKey(QuestionTopic record);
}