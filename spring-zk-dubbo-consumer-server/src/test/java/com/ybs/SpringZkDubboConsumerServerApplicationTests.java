package com.ybs;

import com.ybs.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringZkDubboConsumerServerApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        userService.buyTicket();
    }

}
