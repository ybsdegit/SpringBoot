package com.ybs.controller;

import com.ybs.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * AppController
 *
 * @author Paulson
 * @date 2020/4/7 1:12
 */

@RestController
public class AppController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/send/hello")
    public String sendHello(){
        messageService.sendHello();
        return null;
    }

    @GetMapping("/send/{telPhone}/{code}}")
    public String sendMessage(@PathVariable String telPhone, @PathVariable String code){
        messageService.sendMessage(telPhone, code);
        return "发送成功，请查看手机";
    }

    @GetMapping("/send/{telPhone}")
    public String send(@PathVariable String telPhone){
        messageService.sendMessage(telPhone);
        return "发送成功，请查看手机";
    }

    @GetMapping("/send")
    public String sendTest(){
        messageService.sendMessage();
        return "发送成功，请查看手机";
    }



}
