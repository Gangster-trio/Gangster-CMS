package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.mapper.LogEntryMapper;
import com.gangster.cms.common.pojo.LogEntry;
import com.gangster.cms.common.pojo.LogEntryExample;
import com.ganster.cms.core.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends BaseServiceImpl<LogEntryMapper, LogEntry, LogEntryExample> implements LogService {
}
