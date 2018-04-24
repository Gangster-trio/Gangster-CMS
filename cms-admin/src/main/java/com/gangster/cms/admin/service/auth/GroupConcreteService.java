package com.gangster.cms.admin.service.auth;

import com.gangster.cms.admin.exception.GroupNotFountException;
import com.gangster.cms.admin.exception.UserNotFoundException;
import com.gangster.cms.admin.service.GroupService;
import com.gangster.cms.common.pojo.Group;
import com.gangster.cms.common.pojo.GroupExample;
import com.gangster.cms.common.pojo.User;
import com.gangster.cms.common.pojo.UserExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupConcreteService {

    private static final Logger logger = LoggerFactory.getLogger(GroupConcreteService.class);
    @Autowired
    private GroupService groupService;

    /**
     * 通过用户Id，查找所属于的的用户组
     *
     * @param userId 用户的Id
     * @param page   查找信息的页数
     * @param limit  每页所显示的条数
     * @return PageInfo<Group>
     */
    public PageInfo<Group> findUserGroup(Integer userId, Integer page, Integer limit) {
        GroupExample groupExample=new GroupExample();
        groupExample.or().andGroupIdGreaterThan(userId);
        PageInfo<Group> pageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(() -> groupService.selectByExample(groupExample));
        if (pageInfo==null){
            return null;
        }
        return pageInfo;
    }

    /**
     * 通过用户Id和用户组Id，将用户从用户组中移出
     *
     * @param userId  用户的Id
     * @param groupId 组Id
     * @return 是否删除成功
     */
    public boolean deleteUserGroup(Integer userId, Integer groupId) {
        try {
            groupService.removeUserFromGroup(userId, groupId);
            return true;
        } catch (UserNotFoundException | GroupNotFountException e) {
            logger.error("用户或组不存在");
        }
        return false;
    }

    /**
     * 查找所有的用户组
     *
     * @return 查找到的所有用户组
     */
    public List<Group> findAllGroup() {
        GroupExample groupExample = new GroupExample();
        List<Group> groupList = groupService.selectByExample(groupExample);
        if (groupList != null && !groupList.isEmpty()) {
            return groupList;
        } else return null;
    }

    /**
     * 通过用户Id和用户组Id，来向用户组中添加用户
     *
     * @param groupId 用户组Id
     * @param userId  用户Id
     * @return 是否添加成功
     */
    public boolean addUserToGroup(Integer groupId, Integer userId) {
        List<Group> groupList = groupService.selectByUserId(userId);
        for (Group i : groupList) {
            if (groupId.equals(i.getGroupId())) {
                return false;
            }
        }
        try {
            groupService.addUserToGroup(userId, groupId);
            return true;
        } catch (UserNotFoundException | GroupNotFountException e) {
            e.printStackTrace();
        }
        return false;
    }
}
