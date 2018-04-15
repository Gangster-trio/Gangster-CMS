package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.common.pojo.SiteExample;

public interface SiteService extends BaseService<Site,SiteExample> {
    /**
     * delete all about this site
     * (instead with deleteByPrimaryKey)
     *
     * @param sid Site ID
     */
    int deleteSite(Integer sid);
}
