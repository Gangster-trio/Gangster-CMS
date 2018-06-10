package com.gangster.cms.admin.dto;


import com.gangster.cms.common.pojo.Module;

import java.util.List;

/**
 * Create by Yoke on 2018/1/30
 */
public class ModuleTree {
    private Module module;
    private List<Module> list;

    public ModuleTree() {
    }

    public ModuleTree(Module module, List<Module> list) {
        this.module = module;
        this.list = list;
    }


    @Override
    public String toString() {
        return null;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Module> getList() {
        return list;
    }

    public void setList(List<Module> list) {
        this.list = list;
    }
}
