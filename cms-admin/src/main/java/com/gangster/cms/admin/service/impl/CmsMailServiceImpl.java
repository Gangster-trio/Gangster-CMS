package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.CmsMailService;
import com.gangster.cms.common.pojo.CmsMail;
import com.gangster.cms.common.pojo.CmsMailExample;
import com.gangster.cms.dao.mapper.CmsMailMapper;
import org.springframework.stereotype.Service;

/**
 * @author Yoke
 * Created on 2018/4/24
 */
@Service
public class CmsMailServiceImpl extends BaseServiceImpl<CmsMailMapper,CmsMail,CmsMailExample> implements CmsMailService {
}
