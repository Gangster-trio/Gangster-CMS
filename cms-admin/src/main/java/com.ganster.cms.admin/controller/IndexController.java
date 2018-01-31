package com.ganster.cms.admin.controller;

import com.ganster.cms.admin.common.ModuleTree;
import com.ganster.cms.core.pojo.Module;
import com.ganster.cms.core.pojo.ModuleExample;
import com.ganster.cms.core.service.ArticleService;
import com.ganster.cms.core.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("index")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
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
        modelAndView.addObject("test","test");
        return modelAndView;
//        return treeList;
    }
}