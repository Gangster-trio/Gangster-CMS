package com.ganster.cms.admin.util;

import com.ganster.cms.core.pojo.Module;

import java.util.Comparator;

/**
 * 用于对Module进行排序
 *
 * @author Yoke
 * Created on 2018/4/11
 */
public class ModuleComparator implements Comparator<Module> {
    @Override
    public int compare(Module o1, Module o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 != null && o2 == null) {
            return -1;
        }
        if (o1 == null) {
            return 1;
        }
        if (o1.getModuleSort().compareTo(o2.getModuleSort()) > 0) {
            return 1;
        }
        return -1;
    }
}
