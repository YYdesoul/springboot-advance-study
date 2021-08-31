package com.soul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// AOP: 拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能叶页只有对应有权限的人才能访问
        // 请求授权的规则
        // 链式编程
        http.authorizeRequests()
                .antMatchers("/").permitAll()   // 设置首页所有人都可以访问
                .antMatchers("/level1/**").hasRole("vip1") // 设置/level1/下所有网页的权限
                .antMatchers("/level2/**").hasRole("vip2")  // 设置/level2/下所有网页的权限
                .antMatchers("/level3/**").hasRole("vip3"); // 设置/level3/下所有网页的权限

        // 没有权限默认到登录页,需要开启登录的页面
            // login
//        http.formLogin();
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password");

        // 注销, 然后跳转到首页
        http.logout().logoutSuccessUrl("/");

        // 开启记住我功能
//        http.rememberMe();
    }

    // 认证
    // 密码需要加密
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("xiaoming").password(new BCryptPasswordEncoder().encode("123")).roles("vip2", "vip3")
//                .and()
//                .withUser("xiaohong").password(new BCryptPasswordEncoder().encode("123")).roles("vip1", "vip2", "vip3")
//                .and()
//                .withUser("xiaohua").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");
//
//    }

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("123")
                .roles("vip1")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("123")
                .roles("vip1", "vip2", "vip3")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
