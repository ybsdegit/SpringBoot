package com.ybs;

import com.ybs.pojo.TestTable;
import com.ybs.pojo.User;
import com.ybs.service.PaulsonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SpringJpaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private PaulsonService paulsonService;

    @Test
    void  saveTest(){
        TestTable test = new TestTable();
        test.setCreated(LocalDateTime.now());
        test.setDescription("test");
        paulsonService.saveTest(test);
    }


    @Test
    void saveUser(){
        User user = new User();
        user.setEmail("abc@123.com");
        user.setEnabled(1);
        user.setNickname("test");
        user.setRegTime(LocalDateTime.now());
        paulsonService.saveUser(user);
    }


    @Test
    void findUser(){
        User user = paulsonService.findUserById(20);
        System.out.println(user);
    }

    @Test
    void deleteUser() {
        paulsonService.deleteUser("test", 1);
    }

}
