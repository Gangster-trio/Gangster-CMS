package com.gangster.cms.admin.service.impl;

import com.gangster.cms.admin.base.impl.BaseServiceImpl;
import com.gangster.cms.admin.service.PermissionService;
import com.gangster.cms.common.pojo.Module;
import com.gangster.cms.common.pojo.ModuleExample;
import com.gangster.cms.common.pojo.Permission;
import com.gangster.cms.common.pojo.PermissionExample;
import com.gangster.cms.dao.mapper.ModuleMapper;
import com.gangster.cms.dao.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission, PermissionExample> implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    ModuleMapper moduleMapper;

    private ConcurrentHashMap<String, Integer> moduleCache = new ConcurrentHashMap<>();

    @Override
    public boolean hasPermission(Integer uid, Integer sid, Integer moduleId) {
        if (moduleId == -1) {
            return true;
        }
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.or().andUserIdEqualTo(uid);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);

        for (Permission p : permissionList) {
            if (p.getModuleId().equals(moduleId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasPermission(Integer uid, Integer sid, String moduleName) {
        return hasPermission(uid, sid, getModuleId(moduleName));
    }

    @Override
    public List<Module> getAllPermittedModule(Integer uid, Integer sid) {
        PermissionExample example = new PermissionExample();
        example.or().andSiteIdEqualTo(sid).andUserIdEqualTo(uid);
        List<Permission> permissions = permissionMapper.selectByExample(example);
        return permissions.stream()
                .map(Permission::getModuleId)
                .map(moduleMapper::selectByPrimaryKey)
                .collect(Collectors.toList());
    }


    private Integer getModuleId(String moduleName) {
        Integer id = moduleCache.get(moduleName);
        if (id != null) {
            return id;
        }
        ModuleExample example = new ModuleExample();
        example.or().andModuleNameEqualTo(moduleName);
        List<Module> moduleList = moduleMapper.selectByExample(example);
        if (moduleList.isEmpty()) {
            return -1;
        }
        id = moduleList.get(0).getModuleId();
        moduleCache.put(moduleName, id);
        return id;
    }
}
