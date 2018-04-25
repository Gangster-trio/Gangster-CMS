package com.gangster.cms.dao.mapper;

import java.util.List;

import com.gangster.cms.common.pojo.QuestionPage;
import com.gangster.cms.common.pojo.QuestionPageExample;
import org.apache.ibatis.annotations.Param;

public interface QuestionPageMapper {
    long countByExample(QuestionPageExample example);

    int deleteByExample(QuestionPageExample example);

    int deleteByPrimaryKey(Integer questionPageId);

    int insert(QuestionPage record);

    int insertSelective(QuestionPage record);

    List<QuestionPage> selectByExample(QuestionPageExample example);

    QuestionPage selectByPrimaryKey(Integer questionPageId);

    int updateByExampleSelective(@Param("record") QuestionPage record, @Param("example") QuestionPageExample example);

    int updateByExample(@Param("record") QuestionPage record, @Param("example") QuestionPageExample example);

    int updateByPrimaryKeySelective(QuestionPage record);

    int updateByPrimaryKey(QuestionPage record);
}