package com.ybs.service;

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

    /**
     * 单播（点对点）
     */
    public void sendMsg1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("code", "code");
        rabbitTemplate.convertAndSend("exchange.direct", "ybs_direct", map);
    }

    /**
     * 广播（点对点）
     */
    public void sendMsg2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("code", "code");
        rabbitTemplate.convertAndSend("exchange.fanout", "", map);
    }


}
