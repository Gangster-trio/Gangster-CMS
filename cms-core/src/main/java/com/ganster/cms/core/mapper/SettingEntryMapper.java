package com.ganster.cms.core.mapper;

import com.ganster.cms.core.pojo.SettingEntry;
import com.ganster.cms.core.pojo.SettingEntryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SettingEntryMapper {
    long countByExample(SettingEntryExample example);

    int deleteByExample(SettingEntryExample example);

    int deleteByPrimaryKey(String sysKey);

    int insert(SettingEntry record);

    int insertSelective(SettingEntry record);

    List<SettingEntry> selectByExample(SettingEntryExample example);

    SettingEntry selectByPrimaryKey(String sysKey);

    int updateByExampleSelective(@Param("record") SettingEntry record, @Param("example") SettingEntryExample example);

    int updateByExample(@Param("record") SettingEntry record, @Param("example") SettingEntryExample example);

    int updateByPrimaryKeySelective(SettingEntry record);

    int updateByPrimaryKey(SettingEntry record);
}