package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.LogService;
import com.gangster.cms.common.pojo.LogEntry;
import com.gangster.cms.common.pojo.LogEntryExample;
import com.gangster.cms.dao.mapper.LogEntryMapper;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends BaseServiceImpl<LogEntryMapper, LogEntry, LogEntryExample> implements LogService {
}
