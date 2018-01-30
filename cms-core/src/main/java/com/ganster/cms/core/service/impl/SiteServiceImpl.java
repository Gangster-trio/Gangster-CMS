package com.ganster.cms.core.service.impl;

import com.ganster.cms.core.base.impl.BaseServiceImpl;
import com.ganster.cms.core.dao.mapper.SiteMapper;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.SiteExample;
import com.ganster.cms.core.service.SiteService;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl extends BaseServiceImpl<SiteMapper, Site, SiteExample> implements SiteService {
}
