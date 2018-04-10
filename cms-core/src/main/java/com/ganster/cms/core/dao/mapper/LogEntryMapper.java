package com.ganster.cms.core.dao.mapper;

import com.ganster.cms.core.pojo.LogEntry;
import com.ganster.cms.core.pojo.LogEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogEntryMapper {
    long countByExample(LogEntryExample example);

    int deleteByExample(LogEntryExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(LogEntry record);

    int insertSelective(LogEntry record);

    List<LogEntry> selectByExampleWithBLOBs(LogEntryExample example);

    List<LogEntry> selectByExample(LogEntryExample example);

    LogEntry selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") LogEntry record, @Param("example") LogEntryExample example);

    int updateByExampleWithBLOBs(@Param("record") LogEntry record, @Param("example") LogEntryExample example);

    int updateByExample(@Param("record") LogEntry record, @Param("example") LogEntryExample example);

    int updateByPrimaryKeySelective(LogEntry record);

    int updateByPrimaryKeyWithBLOBs(LogEntry record);

    int updateByPrimaryKey(LogEntry record);
}