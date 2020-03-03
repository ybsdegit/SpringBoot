package com.ybs.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig
 *
 * @author Paulson
 * @date 2020/3/2 22:38
 */

@Configuration
public class ShiroConfig {
    // 3、ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        // 添加 shiro 的内置过滤器

        /*
            anno: 无需认证就可以访问
            anthc: 必须认证才能访问
            user: 必须拥有 记住我 功能才能用
            perms: 拥有对某个资源的权限才能访问
            role: 拥有某个角色权限才能访问
         */

        // 拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 授权,正常情况下，没有授权会跳转到未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");

//        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
        filterMap.put("/user/*", "authc");



        bean.setFilterChainDefinitionMap( filterMap);

        // 设置登录请求
        bean.setLoginUrl("/toLogin");
        // 设置未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    // 2、DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    // 1、创建 Realm 对象，需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


    // 整合 shiroDialect: 用来整合shiro 和 thynmelef
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
