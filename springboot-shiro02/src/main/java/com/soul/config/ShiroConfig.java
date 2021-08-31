package com.soul.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    // ShiroFilterFactoryBean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(securityManager);

        // 添加shiro的内置过滤器，实现拦截
        /*
            anon: 无需认证就可以访问
            authc: 必须认证了才能访问
            user: 必须拥有记住我功能才能访问
            perms: 拥有某个资源的权限才能访问：
            role: 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 拦截
//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/*", "authc");

        // 授权,正常情况下未授权会调到未授权页面
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录的请求
        bean.setLoginUrl("/toLogin");
        // 未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    // DefaulWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联UserRealm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    // 管理数据：创建 realm对象，需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
