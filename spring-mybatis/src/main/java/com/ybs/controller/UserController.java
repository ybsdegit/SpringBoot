package com.ybs.controller;

import com.ybs.mapper.UserMapper;
import com.ybs.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MybatisController
 *
 * @author Paulson
 * @date 2020/2/26 0:23
 */

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    @ResponseBody
    public List<User> queryUserList(){
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user.getName());
        }
        return users;
    }

    @GetMapping("/selectUserById/{id}")
    public User selectUserById(@PathVariable int id){
        User user = userMapper.selectUserById(id);
        return user;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        User user1 = userMapper.selectUserById(user.getId());
        if (user1 != null){
            user1.setName("该用户已存在，请勿重复添加！");
            return user1;
        }
        userMapper.addUser(user);
        return userMapper.selectUserById(user.getId());
    }


    @GetMapping("/updateUser/{id}/{name}")
    public User updateUser(@PathVariable(value = "id") int id, @PathVariable(value = "name") String name){
        User user = new User();
        user.setName(name);
        user.setId(id);
        user.setPwd("pwd");
        userMapper.updateUser(user);
        return userMapper.selectUserById(user.getId());
    }

    @GetMapping("/updateUser/{id}")
    public String deleteUser(@PathVariable(value = "id") int id){
        userMapper.deleteUser(id);
        return "user";
    }



}
