package com.ybs.mq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Customer1
 *
 * @author Paulson
 * @date 2020/1/2 23:04
 */

@Component
//@RabbitListener(queues = "wier")  // 正常情况下rabbit mq不会自动创建队列
@RabbitListener(queuesToDeclare = @Queue("wier"))  // 自己去创建这个队列的监听
public class Customer3 {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("wier收到消费消息: "+msg);
    }

}
