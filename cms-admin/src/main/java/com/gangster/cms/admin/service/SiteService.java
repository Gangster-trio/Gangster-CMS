package com.gangster.cms.admin.service;

import com.gangster.cms.admin.base.BaseService;
import com.gangster.cms.common.pojo.Site;
import com.gangster.cms.common.pojo.SiteExample;

public interface SiteService extends BaseService<Site, SiteExample> {
    /**
     * delete all about this site
     * (instead with deleteByPrimaryKey)
     *
     * @param sid Site ID
     */
    int deleteSite(Integer sid);

    void deleteBatchSite(String siteIdStr);
}
