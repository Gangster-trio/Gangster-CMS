package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.dto.ModuleTree;
import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Module;
import com.ganster.cms.core.pojo.ModuleExample;
import com.ganster.cms.core.pojo.Site;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@Controller
public class IndexController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ArticleService articleService;
    @Autowired
    private SiteService siteService;

    @Autowired
    private UserService userService;

    @Autowired
    PermissionService permissionService;

    //    @RequiresPermissions("super")
    @GetMapping("/index")
    public ModelAndView index() {
        Subject subject = SecurityUtils.getSubject();
        Integer id = (Integer) subject.getSession().getAttribute("id");
        User user = userService.selectByPrimaryKey(id);
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Site> siteList = permissionService.findAllUserSite(id);
            List<ModuleTree> treeList = new ArrayList<ModuleTree>();
            ModuleExample moduleExample = new ModuleExample();
            moduleExample.or().andModuleParentIdEqualTo(0);
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
            modelAndView.addObject("treelist", treeList);
            modelAndView.addObject("test", "test");
            modelAndView.addObject("sites", siteList);
        } catch (GroupNotFountException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}