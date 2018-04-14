package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.ModuleTree;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Module;
import com.ganster.cms.core.pojo.ModuleExample;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.ModuleService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.SiteService;
import com.ganster.cms.core.service.UserService;
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
    private SiteService siteService;

    @Autowired
    private UserService userService;

    @Autowired
    PermissionService permissionService;

    //    @RequiresPermissions("super")
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(required = false) Integer id) {

        Subject subject = SecurityUtils.getSubject();
        Integer userId = (Integer) subject.getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(userId);
        if (id != null) {
            LOGGER.info("用户id为{},名字为{} 刷新权限", userId, user.getUserName());
            PermissionUtil.flush(userId);

        }
        ModelAndView modelAndView = new ModelAndView();

        //查出所有的父模块
        List<ModuleTree> treeList = new ArrayList<>();
        ModuleExample moduleExample = new ModuleExample();
        if (user.getUserName().equals("admin")) {
            moduleExample.or().andModuleParentIdEqualTo(0);
        } else {
            moduleExample.or().andModuleParentIdEqualTo(0).andModuleIdNotEqualTo(4).andModuleIdNotEqualTo(8);
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

        List siteList = new ArrayList();
        try {
            siteList = permissionService.findAllUserSite(userId);
        } catch (GroupNotFountException e) {
            e.printStackTrace();
        }
        GroupController.refresh();
        modelAndView.addObject("moduleTreeList", treeList);
        modelAndView.addObject("siteList", siteList);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}