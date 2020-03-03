package com.ybs.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MyController
 *
 * @author Paulson
 * @date 2020/3/2 22:30
 */

@Controller
@Slf4j
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg", "hello, Shiro!");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String login(){
        return "login";
    }

    @RequestMapping("/login")
    public String logint(String username, String password, Model model){
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token); // 执行登录方法，如果没有异常，登录成功
            return "index";
        }catch (UnknownAccountException e){
            log.error("用户名不存在");
            model.addAttribute("msg", "用户名不存在");
        }catch (IncorrectCredentialsException e){
            log.error("密码错误");
            model.addAttribute("msg", "密码错误");
        }
        return "login";
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String Unauthorized(){
        return "未经授权,无法访问此页面";
    }

}
