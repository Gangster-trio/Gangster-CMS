package com.gangster.cms.admin.controller;

import com.gangster.cms.admin.annotation.SystemControllerLog;
import com.gangster.cms.admin.dto.ModuleTree;
import com.gangster.cms.admin.service.CmsMailService;
import com.gangster.cms.admin.service.ModuleService;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.admin.service.SiteService;
import com.gangster.cms.admin.util.PermissionUtil;
import com.gangster.cms.common.constant.CmsConst;
import com.gangster.cms.common.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

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

    private static final String PERMISSION_MANAGER = "权限管理";

    private static final Integer ROOT_MODULE_PARENT_ID = 0;

    @SystemControllerLog(description = "到了主界面")
    @GetMapping({"/index", "/"})
    public ModelAndView index(@SessionAttribute(CmsConst.CURRENT_USER) User user, @RequestParam(required = false) Integer id) {
        // TODO: 2018/4/15 待优化
        if (id != null) {
            LOGGER.info("用户id为{},名字为{} 刷新权限", user.getUserId(), user.getUserName());
            PermissionUtil.flush(user.getUserId());
        }
        ModelAndView modelAndView = new ModelAndView();

        //查出所有的父模块
        ModuleExample moduleExample = new ModuleExample();
        if (!user.getUserIsAdmin()) {
            moduleExample.or().andModuleParentIdEqualTo(ROOT_MODULE_PARENT_ID).andModuleNameNotEqualTo(PERMISSION_MANAGER);
        } else {
            moduleExample.or().andModuleParentIdEqualTo(ROOT_MODULE_PARENT_ID);
        }

        List<Integer> siteIdList = PermissionUtil.getAllPermissionSite(user.getUserId());
        List<Site> siteList = siteIdList.stream().sorted(Comparator.comparingInt(val -> val)).map(i -> siteService.selectByPrimaryKey(i)).filter(Objects::nonNull).collect(Collectors.toList());

        // 列出当前的登陆用户未读的邮件
        CmsMailExample cmsMailExample = new CmsMailExample();
        cmsMailExample.or().andMailToMailEqualTo(user.getUserEmail()).andMailReadEqualTo(CmsConst.MAIIL_READ_TOREAD).andMailFlagStatusEqualTo(CmsConst.MAIL_FLAG_SENDED);

        List<CmsMail> mailList = cmsMailService.selectByExample(cmsMailExample);
        // 将当前登录网站为list的第一个
        GroupController.refresh();
        modelAndView.addObject("moduleTreeList", listModule(moduleExample));
        modelAndView.addObject("mailTotalNum", mailList == null ? 0 : mailList.size());
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    private List<ModuleTree> listModule(ModuleExample moduleExample) {
        return moduleService.selectByExample(moduleExample)
                .stream().map(module -> {
                    moduleExample.clear();
                    moduleExample.or().andModuleParentIdEqualTo(module.getModuleId());
                    ModuleTree moduleTree = new ModuleTree();
                    moduleTree.setModule(module);
                    moduleTree.setList(moduleService.selectByExample(moduleExample));
                    return moduleTree;
                }).collect(Collectors.toList());
    }
}