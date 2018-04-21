package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.CountService;
import com.gangster.cms.common.pojo.CountEntry;
import com.gangster.cms.common.pojo.CountEntryExample;
import com.gangster.cms.dao.mapper.CountEntryMapper;
import org.springframework.stereotype.Service;

@Service
public class CountServiceImpl extends BaseServiceImpl<CountEntryMapper,CountEntry,CountEntryExample> implements CountService{
}
