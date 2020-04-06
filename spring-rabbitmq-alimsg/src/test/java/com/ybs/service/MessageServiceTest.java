package com.ybs.service;

import com.ybs.RabbitAliMessage;
import com.ybs.utils.SendSms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * MessageServiceTest
 *
 * @author Paulson
 * @date 2020/4/7 1:21
 */
@SpringBootTest
@ContextConfiguration(classes = RabbitAliMessage.class)
class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    void test(){
        messageService.sendMessage("18810911636", "123456");
    }

    @Test
    void test2(){
        SendSms.sendCode("18810911636");
    }
}