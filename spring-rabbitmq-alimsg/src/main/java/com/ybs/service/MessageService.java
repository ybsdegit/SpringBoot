package com.ybs.service;

import com.ybs.utils.SendSms;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * MessageService
 *
 * @author Paulson
 * @date 2020/4/7 1:14
 */

@Component
public class MessageService {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendHello() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend("hello", context);
    }





    public void sendMessage() {
        rabbitTemplate.convertAndSend("sendMessage", "18810911636");
    }

    public void sendMessage(String telPhone) {
        rabbitTemplate.convertAndSend("sendMessage", "18810911636");

    }

    public void sendMessage(String telPhone, String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telPhone", telPhone);
        map.put("code", code);
        rabbitTemplate.convertAndSend("sendMessage", map);
    }
}
