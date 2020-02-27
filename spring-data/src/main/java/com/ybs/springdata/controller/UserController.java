package com.ybs.springdata.controller;

import com.ybs.springdata.mapper.UserMapper;
import com.ybs.springdata.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<User> queryUserList(){
        List<User> users = userMapper.selectUser();
        for (User user : users) {
            System.out.println(user.getName());
        }
        return users;
    }

}
