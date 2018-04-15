package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.ModuleTree;
import com.ganster.cms.admin.web.CmsCommonBean;
import com.ganster.cms.core.pojo.ModuleExample;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.ModuleService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.util.PermissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    private CmsCommonBean cmsCommonBean;
    @Autowired
    private SiteService siteService;

    private static final String PERMISSION_MANAGER = "权限管理";

    private static final Integer ROOT_MODULE_PARENT_ID = 0;

    @SystemControllerLog(description = "到了主界面")
    @GetMapping({"/index", "/"})
    public ModelAndView index(@RequestParam(required = false) Integer id) {

        User user = cmsCommonBean.getUser();
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
        List<Site> siteList = siteIdList.stream().sorted((val1, val2) -> val1 - val2).map(i -> siteService.selectByPrimaryKey(i)).filter(Objects::nonNull).collect(Collectors.toList());
        // 将当前登录网站为list的第一个
        cmsCommonBean.setSite(siteList.get(0));
        GroupController.refresh();
        modelAndView.addObject("moduleTreeList", listModule(moduleExample));
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