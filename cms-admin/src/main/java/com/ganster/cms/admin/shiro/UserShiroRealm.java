package com.ganster.cms.admin.shiro;

import com.ganster.cms.admin.web.CmsCommonBean;
import com.ganster.cms.core.pojo.Group;
import com.ganster.cms.core.pojo.Permission;
import com.ganster.cms.core.pojo.User;
import com.ganster.cms.core.pojo.UserExample;
import com.ganster.cms.core.service.GroupService;
import com.ganster.cms.core.service.PermissionService;
import com.ganster.cms.core.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserShiroRealm.class);

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private GroupService groupService;
    @Resource
    private CmsCommonBean commonBean;

    /**
     * 认证信息.(身份验证)
     * Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        logger.debug("用户登录：{}  凭证：{}", username, password);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        List<User> users = userService.selectByExample(userExample);
        if (users.isEmpty()) {
            throw new UnknownAccountException();
        }
        User user = users.get(0);
        if (!user.getUserPassword().equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //此方法废弃
        SecurityUtils.getSubject().getSession().setAttribute("id", user.getUserId());
        commonBean.setUser(user);
        logger.info("当前登录的用户信息：" + commonBean.getUser().toString());
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();

        List<Group> groupList = groupService.selectByUserId(user.getUserId());

        Set<String> groupSet = groupList.stream().map(Group::getGroupName).collect(Collectors.toSet());

        Set<String> permissionSet = groupSet
                .stream()
                .flatMap(group -> permissionService.selectByGroupName(group)
                        .stream()
                        .map(Permission::getPermissionName))
                .collect(Collectors.toSet());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        simpleAuthorizationInfo.setRoles(groupSet);
        return simpleAuthorizationInfo;
    }
}
