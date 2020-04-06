package com.ybs.contumer;

import com.ybs.utils.SendSms;
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

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))  // 自己去创建这个队列的监听
public class hello {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("hello! 收到消费消息: "+msg);
    }

}
