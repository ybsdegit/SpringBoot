package com.ybs.config;

import com.ybs.pojo.User;
import com.ybs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserRealm
 *
 * @author Paulson
 * @date 2020/3/2 22:40
 */

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行了 =》授权 doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        // 设置当前的用户权限
        info.addStringPermission(currentUser.getPerms());


        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行了 =》认证 doGetAuthorizationInfo");
        // 用户名、密码 数据库中取

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null){
            return null; // 抛出异常 UnknownAccountException
        }
        // 密码认证
        return new SimpleAuthenticationInfo(user, user.getPwd(),"");
    }
}
