package com.ganster.cms.core.service;

import com.ganster.cms.core.base.BaseService;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.SiteExample;

public interface SiteService extends BaseService<Site,SiteExample> {
    /**
     * delete all about this site
     * (instead with deleteByPrimaryKey)
     *
     * @param sid Site ID
     */
    int deleteSite(Integer sid);
}
