package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.mapper.CountEntryMapper;
import com.gangster.cms.common.pojo.CountEntry;
import com.gangster.cms.common.pojo.CountEntryExample;
import com.ganster.cms.core.service.CountService;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl extends BaseServiceImpl<CountEntryMapper,CountEntry,CountEntryExample> implements CountService{
}
