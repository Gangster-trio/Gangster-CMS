package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.gangster.cms.common.pojo.SettingEntry;
import com.gangster.cms.common.pojo.SettingEntryExample;

public interface SettingService extends BaseService<SettingEntry,SettingEntryExample> {
    String get(String key);
    String put(String key,String val);
    void del(String key);
}
