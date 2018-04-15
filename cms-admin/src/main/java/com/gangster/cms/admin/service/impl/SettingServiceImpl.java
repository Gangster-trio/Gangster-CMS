package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.SettingService;
import com.gangster.cms.common.pojo.SettingEntry;
import com.gangster.cms.common.pojo.SettingEntryExample;
import com.gangster.cms.dao.mapper.SettingEntryMapper;
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
