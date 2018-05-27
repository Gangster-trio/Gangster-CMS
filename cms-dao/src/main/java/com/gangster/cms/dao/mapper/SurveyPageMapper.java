package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.SurveyPage;
import com.gangster.cms.common.pojo.SurveyPageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyPageMapper {
    long countByExample(SurveyPageExample example);

    int deleteByExample(SurveyPageExample example);

    int deleteByPrimaryKey(Integer pageId);

    int insert(SurveyPage record);

    int insertSelective(SurveyPage record);

    List<SurveyPage> selectByExample(SurveyPageExample example);

    SurveyPage selectByPrimaryKey(Integer pageId);

    int updateByExampleSelective(@Param("record") SurveyPage record, @Param("example") SurveyPageExample example);

    int updateByExample(@Param("record") SurveyPage record, @Param("example") SurveyPageExample example);

    int updateByPrimaryKeySelective(SurveyPage record);

    int updateByPrimaryKey(SurveyPage record);
}