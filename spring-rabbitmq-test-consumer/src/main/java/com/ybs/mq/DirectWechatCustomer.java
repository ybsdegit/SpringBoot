package com.ybs.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Customer1
 *
 * @author Paulson
 * @date 2020/1/2 23:04
 */

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${order.direct.wechat.queue}", autoDelete = "true"),
        exchange = @Exchange(value = "${order.direct.exchange}", type= ExchangeTypes.DIRECT),
        key="wechat"
))
public class DirectWechatCustomer {

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("wechat direct ---------->: "+msg);
    }

}

