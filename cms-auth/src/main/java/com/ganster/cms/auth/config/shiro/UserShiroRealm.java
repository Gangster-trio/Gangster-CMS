package com.ganster.cms.auth.config.shiro;

import com.ganster.cms.core.exception.GroupNotFountException;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import com.ganster.cms.core.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;


public class UserShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserShiroRealm.class);

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private GroupService groupService;
    private Integer userId;

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        logger.info("-----------------------------" + password + "--------------------------------------");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        List<User> users = userService.selectByExample(userExample);
        Integer j = 0;
        for (User i : users) {
            userId = i.getUserId();
            j++;
        }
        if (j >= 2) {
            throw new AuthenticationException();
        }
        User user = userService.selectByPrimaryKey(userId);
        if (!user.getUserName().equals(username)) {
            SecurityUtils.getSubject().logout();
            throw new AuthenticationException();
        }
        if (user == null) {
            throw new AuthenticationException();
        }
        SecurityUtils.getSubject().getSession().setAttribute("id", user.getUserId());
        logger.info("用户" + user.getUserName() + "进行认证");
        if (!Objects.equals(password, user.getUserPassword())) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("进入权限配置");
        String username = (String) principals.getPrimaryPrincipal();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        List<User> users = userService.selectByExample(userExample);
        Integer j = 0;
        for (User i : users) {
            userId = i.getUserId();
            j++;
        }
        if (j >= 2) {
            return null;
        }
        User user = userService.selectByPrimaryKey(userId);

        List<Group> groupList = groupService.selectByUserId(user.getUserId());

        Set<String> groupSet = new HashSet<>();

        for (Group i : groupList) {
            if (!StringUtil.isNullOrEmpty(user.getUserName())) {
                groupSet.add(i.getGroupName());
            }
        }

        Set<String> permissionSet = new HashSet<>();
        for (Group i : groupList) {
            if (!StringUtil.isNullOrEmpty(i.getGroupName())) {
                try {
                    List<Permission> permissions = permissionService.selectByGroupId(i.getGroupId());
                    for (Permission permission : permissions) {
                        permissionSet.add(permission.getPermissionName());
                    }
                } catch (GroupNotFountException e) {
                    logger.info("角色未找到");
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        simpleAuthorizationInfo.setRoles(groupSet);
        return simpleAuthorizationInfo;

    }

    /**
     *
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     */

}
