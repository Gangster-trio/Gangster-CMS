package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.mapper.SettingEntryMapper;
import com.ganster.cms.core.pojo.SettingEntry;
import com.ganster.cms.core.pojo.SettingEntryExample;
import com.ganster.cms.core.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SettingServiceImpl extends BaseServiceImpl<SettingEntryMapper, SettingEntry, SettingEntryExample> implements SettingService {
    @Autowired
    SettingEntryMapper settingEntryMapper;

    @Override
    public String get(String key) {
        SettingEntry entry = mapper.selectByPrimaryKey(key);
        if (entry != null)
            return entry.getSysValue();
        return "";
    }

    @Override
    public String put(String key, String val) {
        SettingEntry entry = mapper.selectByPrimaryKey(key);
        if (entry != null) {
            String tmp = entry.getSysValue();
            entry.setSysValue(val);
            entry.setSysUpdateTime(new Date());
            mapper.updateByPrimaryKey(entry);
            return tmp;
        }
        entry = new SettingEntry();
        entry.setSysKey(key);
        entry.setSysValue(val);
        entry.setSysCreateTime(new Date());
        mapper.insert(entry);
        return "";
    }

    @Override
    public void del(String key) {
        mapper.deleteByPrimaryKey(key);
    }
}
