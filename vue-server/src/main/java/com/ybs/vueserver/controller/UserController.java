package com.ybs.vueserver.controller;

import com.ybs.vueserver.entity.User;
import com.ybs.vueserver.service.UserService;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * UserController
 *
 * @author Paulson
 * @date 2020/3/10 21:49
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/findAll/{page}/{size}")
    public Page<User> findPage(@PathVariable("page") int page, @PathVariable("size") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return userService.findAll(pageRequest);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        User result = userService.save(user);
        if (result!= null){
            return "success";
        }
        return "error";
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        userService.deleteById(id);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userService.findById(id).get();
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        User result = userService.save(user);
        if (result!= null){
            return "success";
        }
        return "error";
    }

}
