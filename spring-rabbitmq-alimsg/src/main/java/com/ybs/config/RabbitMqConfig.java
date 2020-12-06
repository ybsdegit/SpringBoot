package com.ybs.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitMqConfig
 * @Author Paulson
 * @Date 2020/6/21
 * @Description:
 */

@Configuration
public class RabbitMqConfig {

    /**
     * mq message 序列化为json
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
