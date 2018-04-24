package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.QuestionAnswer;
import com.gangster.cms.common.pojo.QuestionAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionAnswerMapper {
    long countByExample(QuestionAnswerExample example);

    int deleteByExample(QuestionAnswerExample example);

    int deleteByPrimaryKey(Integer answerId);

    int insert(QuestionAnswer record);

    int insertSelective(QuestionAnswer record);

    List<QuestionAnswer> selectByExample(QuestionAnswerExample example);

    QuestionAnswer selectByPrimaryKey(Integer answerId);

    int updateByExampleSelective(@Param("record") QuestionAnswer record, @Param("example") QuestionAnswerExample example);

    int updateByExample(@Param("record") QuestionAnswer record, @Param("example") QuestionAnswerExample example);

    int updateByPrimaryKeySelective(QuestionAnswer record);

    int updateByPrimaryKey(QuestionAnswer record);
}