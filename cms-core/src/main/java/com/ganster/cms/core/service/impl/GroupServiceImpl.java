package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.GroupMapper;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper,Group,GroupExample> implements GroupService {
}
