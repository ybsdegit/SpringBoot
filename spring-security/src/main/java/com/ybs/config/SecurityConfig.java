package com.ybs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfig
 *
 * @author Paulson
 * @date 2020/2/28 1:10
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能也只有对应的有权限的人使用
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        // 没有权限默认到登录页面,开启登录页面
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        // 注销
        // 防止网络攻击： get pst
        //
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/");

        // 开启记住我
        http.rememberMe();
    }

    /**
     * 认证
     * 密码编码：PasswordEncode
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 这些数据正常从数据库中读取

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("paulson").password(new BCryptPasswordEncoder().encode("mima")).roles("vip2", "vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("mima")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("mima")).roles("vip1");

    }

}
