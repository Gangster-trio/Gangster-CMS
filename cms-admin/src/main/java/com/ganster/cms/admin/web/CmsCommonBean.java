package com.ganster.cms.admin.web;

import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.User;
import org.springframework.context.annotation.Bean;

/**
 * @author Yoke
 * Created on 2018/4/13
 */

public class CmsCommonBean {
    private Site site;
    private User user;

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
