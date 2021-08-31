package com.soul.config;

import com.soul.pojo.User;
import com.soul.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=> 授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 所有人都有add权限了
//        info.addStringPermission("user:add");

        //
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser.getName().equals("小红")) {
            info.addStringPermission("user:add");
        }

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=> 认证doGetAuthenticationInfo");

        // 用户名，密码
//        String name = "root";
//        String password = "123";
//        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
//        if (!userToken.getUsername().equals(name)) {
//            return null;    // 抛出异常
//        }
//        // 密码认证shiro做
//        return new SimpleAuthenticationInfo("", password, "");

        // 连接真实数据库
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String name = userToken.getUsername();
        User user = userService.getUserByname(name);
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPwd(), "");   //第一个参数是想传递的对象
    }
}
