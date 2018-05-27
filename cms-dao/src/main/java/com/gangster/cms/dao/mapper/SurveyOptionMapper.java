package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.SurveyOption;
import com.gangster.cms.common.pojo.SurveyOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SurveyOptionMapper {
    long countByExample(SurveyOptionExample example);

    int deleteByExample(SurveyOptionExample example);

    int deleteByPrimaryKey(Integer optionId);

    int insert(SurveyOption record);

    int insertSelective(SurveyOption record);

    List<SurveyOption> selectByExample(SurveyOptionExample example);

    SurveyOption selectByPrimaryKey(Integer optionId);

    int updateByExampleSelective(@Param("record") SurveyOption record, @Param("example") SurveyOptionExample example);

    int updateByExample(@Param("record") SurveyOption record, @Param("example") SurveyOptionExample example);

    int updateByPrimaryKeySelective(SurveyOption record);

    int updateByPrimaryKey(SurveyOption record);
}