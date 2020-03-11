package com.ybs.vueserver.service;

import com.ybs.vueserver.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserServiceTest
 *
 * @author Paulson
 * @date 2020/3/10 21:42
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    void findAll(){
        List<User> all = userService.findAll();
        System.out.println(all);
    }
    @Test
    void findAllPage(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<User> page = userService.findAll(pageRequest);
        int i = 0;
    }

}