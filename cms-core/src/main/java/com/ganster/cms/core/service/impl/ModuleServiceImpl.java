package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.ModuleMapper;
import com.ganster.cms.core.pojo.Module;
import com.ganster.cms.core.pojo.ModuleExample;
import com.ganster.cms.core.service.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper,Module,ModuleExample> implements ModuleService {
}
