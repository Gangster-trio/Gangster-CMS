package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.annotation.SystemControllerLog;
import com.ganster.cms.admin.dto.ModuleTree;
import com.ganster.cms.core.pojo.Module;
import com.ganster.cms.core.pojo.ModuleExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.ModuleService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private static final String ADMIN = "admin";

    @SystemControllerLog(description = "到了主界面")
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) Integer id) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (id != null) {
            LOGGER.info("用户id为{},名字为{} 刷新权限", user.getUserId(), user.getUserName());
            PermissionUtil.flush(user.getUserId());
        }
        ModelAndView modelAndView = new ModelAndView();

        //查出所有的父模块
        List<ModuleTree> treeList = new ArrayList<>();
        ModuleExample moduleExample = new ModuleExample();
        if (ADMIN.equals(user.getUserName())) {
            moduleExample.or().andModuleParentIdEqualTo(0);
        } else {
            List<Integer> commonNotModuleIds = Arrays.asList(4, 8, 10, 11);
            moduleExample.or().andModuleParentIdEqualTo(0).andModuleIdNotIn(commonNotModuleIds);
        }
        List<Module> parents = moduleService.selectByExample(moduleExample);
        for (Module module : parents) {
            ModuleExample moduleExample1 = new ModuleExample();
            moduleExample1.or().andModuleParentIdEqualTo(module.getModuleId());
            List<Module> childs = moduleService.selectByExample(moduleExample1);
            ModuleTree moduleTree = new ModuleTree();
            moduleTree.setModule(module);
            moduleTree.setList(childs);
            treeList.add(moduleTree);
        }

        List siteList = permissionService.findAllUserSite(user.getUserId());

        modelAndView.addObject("moduleTreeList", treeList);
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}