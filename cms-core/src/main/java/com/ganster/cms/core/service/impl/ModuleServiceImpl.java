package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.mapper.ModuleMapper;
import com.gangster.cms.common.pojo.Module;
import com.gangster.cms.common.pojo.ModuleExample;
import com.ganster.cms.core.service.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper,Module,ModuleExample> implements ModuleService {
}
