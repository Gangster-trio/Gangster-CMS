package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.ModuleService;
import com.gangster.cms.common.pojo.Module;
import com.gangster.cms.common.pojo.ModuleExample;
import com.gangster.cms.dao.mapper.ModuleMapper;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper,Module,ModuleExample> implements ModuleService {
}
