package com.gangster.cms.admin.service.web;

import com.gangster.cms.admin.service.OuterChainService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.common.pojo.OuterChain;
import com.gangster.cms.common.pojo.OuterChainExample;
import com.gangster.cms.common.pojo.Site;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Yoke
 * Created on 2018/4/23
 */
@Service
public class OuterChainWebService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterChainWebService.class);
    @Autowired
    private OuterChainService outerChainService;

    @Autowired
    private SiteService siteService;

    public PageInfo<OuterChain> list(Integer siteId, Integer page, Integer limit) {
        OuterChainExample outerChainExample = new OuterChainExample();
        outerChainExample.or().andOuterchainSiteIdEqualTo(siteId);
        return PageHelper.startPage(page, limit).doSelectPageInfo(() -> outerChainService.selectByExample(outerChainExample));
    }

    public OuterChain details(Integer id) {
        return outerChainService.selectByPrimaryKey(id);
    }

    public boolean add(Integer siteId, OuterChain outerChain) {
        Site site = siteService.selectByPrimaryKey(siteId);
        if (null == site) {
            return false;
        }
        outerChain.setOuterchainSiteId(siteId);
        outerChain.setOuterchainCreateTime(new Date());
        return outerChainService.insert(outerChain) != 0;
    }

    public boolean delete(Integer id) {
        OuterChain outerChain = outerChainService.selectByPrimaryKey(id);
        if (null == outerChain) {
            return false;
        } else {
            return outerChainService.deleteByPrimaryKey(id) != 0;
        }
    }

    public boolean update(Integer id, OuterChain outerChain) {
        outerChain.setOuterchainId(id);
        outerChain.setOuterchainUpdateTime(new Date());
        return outerChainService.updateByPrimaryKeySelective(outerChain) != 0;
    }
}
