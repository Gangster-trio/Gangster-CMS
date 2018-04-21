package com.gangster.cms.dao.mapper;

import com.gangster.cms.common.pojo.CountEntry;
import com.gangster.cms.common.pojo.CountEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountEntryMapper {
    long countByExample(CountEntryExample example);

    int deleteByExample(CountEntryExample example);

    int deleteByPrimaryKey(Integer countId);

    int insert(CountEntry record);

    int insertSelective(CountEntry record);

    List<CountEntry> selectByExample(CountEntryExample example);

    CountEntry selectByPrimaryKey(Integer countId);

    int updateByExampleSelective(@Param("record") CountEntry record, @Param("example") CountEntryExample example);

    int updateByExample(@Param("record") CountEntry record, @Param("example") CountEntryExample example);

    int updateByPrimaryKeySelective(CountEntry record);

    int updateByPrimaryKey(CountEntry record);
}