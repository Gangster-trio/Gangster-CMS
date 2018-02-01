package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.pojo.SettingEntry;
import com.ganster.cms.core.pojo.SettingEntryExample;

public interface SettingService extends BaseService<SettingEntry,SettingEntryExample> {
    String get(String key);
    String put(String key,String val);
    void del(String key);
}
