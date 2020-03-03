package com.ybs;

import com.ybs.pojo.User;
import com.ybs.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringShiroWebApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = userService.queryUserByName("钟鸣");
        System.out.println(user);
    }

}
