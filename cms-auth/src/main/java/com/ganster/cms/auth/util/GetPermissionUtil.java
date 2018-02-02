package com.ganster.cms.auth.util;

import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.GroupExample;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetPermissionUtil {
    private static final Logger logger= LoggerFactory.getLogger(GetPermissionUtil.class);

    @Resource
    private GroupService groupService;

    @Resource
    private PermissionService permissionService;

    public List<String> getPermissionName(String groupName) {
        List<String> permissionName = new ArrayList<>();
        PInformationUtil pInformationUtil = new PInformationUtil();
        GroupExample groupExample = new GroupExample();
        logger.info("++++++++++++++++"+groupService);
        logger.info("+++++++++++++++++++"+groupName);
        groupExample.createCriteria().andGroupNameEqualTo(groupName);
        logger.info("++++++++++++++++++++++"+groupExample.toString());
        List<Group> groupList = groupService.selectByExample(groupExample);
        for (Group group : groupList) {
            try {
                List<Permission> permissionList = permissionService.selectByGroupId(group.getGroupId());
                for (Permission i : permissionList) {
                    int j = 0;
                    try {
                        pInformationUtil.dealInfromation(i.getPermissionName());
                        String name = "findgroup" + ":" + pInformationUtil.getId();
                        permissionName.add(name);
                        j++;
                    } catch (Exception e) {
                        break;
                    }
                }
            } catch (GroupNotFountException e) {
                e.printStackTrace();
            }
        }
        return permissionName;
    }
}
