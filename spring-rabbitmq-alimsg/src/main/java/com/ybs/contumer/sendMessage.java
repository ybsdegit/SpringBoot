package com.ybs.contumer;

import com.ybs.utils.SendSms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * sendMessage
 *
 * @author Paulson
 * @date 2020/4/7 1:17
 */

@Slf4j
@Component
@RabbitListener(queuesToDeclare = @Queue("sendMessage"))  // 自己去创建这个队列的监听
public class sendMessage {

    @RabbitHandler
    public void sendMag(HashMap map){
        String telPhone = (String) map.get("telPhone");
        String code = (String) map.get("code");
        SendSms.sendCode(telPhone, code);
        log.info("发送短信成功{}",code);
    }

    @RabbitHandler
    public void sendMag(String telPhone){
        String code = SendSms.sendCode(telPhone);
        log.info("发送短信成功{}",code);
    }

}
