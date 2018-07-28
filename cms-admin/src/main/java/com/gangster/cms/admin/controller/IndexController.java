package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.ModuleTree;
import com.gangster.cms.admin.service.CmsMailService;
import com.gangster.cms.admin.service.ModuleService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Create by Yoke on 2018/1/30
 */
@Controller
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ModuleService moduleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    private SiteService siteService;
    @Autowired
    private CmsMailService cmsMailService;

    private static final Integer ROOT_MODULE_PARENT_ID = 0;

    @SystemControllerLog(description = "到了主界面")
    @GetMapping({"/index", "/"})
    public ModelAndView index(@SessionAttribute(CmsConst.CURRENT_USER) User user
            , @RequestParam(required = false, value = "siteId") Integer siteId) {
        Site currentSite = null;

        if (siteId != null) {
            currentSite = siteService.selectByPrimaryKey(siteId);
            if (currentSite == null) {
                LOGGER.info("站点{} 不存在", siteId);
            }
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/index");
        List<Integer> siteIdList = siteService.selectByExample(new SiteExample())
                .stream()
                .map(Site::getSiteId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Site> siteList;
        if (!siteIdList.isEmpty()) {
            SiteExample siteExample = new SiteExample();
            siteExample.or().andSiteIdIn(siteIdList);
            siteList = siteService.selectByExample(siteExample)
                    .stream()
                    .filter(Objects::nonNull)
                    .sorted(Comparator.comparing(Site::getSiteId))
                    .collect(Collectors.toList());
        } else {
            siteList = Collections.emptyList();
        }

        if (currentSite == null && !siteIdList.isEmpty()) {
            currentSite = siteList.get(0);
        }

        //查出所有的父模块
        ModuleExample moduleExample = new ModuleExample();
        moduleExample.or().andModuleParentIdEqualTo(ROOT_MODULE_PARENT_ID);

        if (currentSite != null) {

            Site finalCurrentSite = currentSite;
            List moduleTreeList = moduleService.selectByExample(moduleExample)
                    .stream()
                    .filter(module -> permissionService
                            .hasPermission(user.getUserId(), finalCurrentSite.getSiteId(), module.getModuleId()))
                    .map(module -> {
                        moduleExample.clear();
                        moduleExample.or().andModuleParentIdEqualTo(module.getModuleId());
                        ModuleTree moduleTree = new ModuleTree();
                        moduleTree.setModule(module);
                        moduleTree.setList(moduleService.selectByExample(moduleExample));
                        return moduleTree;
                    }).collect(Collectors.toList());
            modelAndView.addObject("moduleTreeList", moduleTreeList);
        } else {
            currentSite = new Site();
        }
        modelAndView.addObject("currentSite", currentSite);
        // 列出当前的登陆用户未读的邮件
        CmsMailExample cmsMailExample = new CmsMailExample();
        cmsMailExample.or().andMailToMailEqualTo(user.getUserEmail()).andMailReadEqualTo(CmsConst.MAIIL_READ_TOREAD).andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED);

        List<CmsMail> mailList = cmsMailService.selectByExample(cmsMailExample);
        modelAndView.addObject("mailTotalNum", mailList == null ? 0 : mailList.size());
        // 将当前登录网站为list的第一个
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}

