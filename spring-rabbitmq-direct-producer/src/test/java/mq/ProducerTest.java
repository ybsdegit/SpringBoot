package mq;

import com.ybs.DirectProducerApplication;
import com.ybs.mq.DirectProducer;
import com.ybs.mq.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

/**
 * ProducerTest
 *
 * @author Paulson
 * @date 2020/4/6 23:47
 */
@SpringBootTest
@ContextConfiguration(classes = DirectProducerApplication.class)
public class ProducerTest {

    @Autowired
    private DirectProducer producer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            producer.sendMessage(i);
        }
    }

    @Test
    void test1() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            producer.sendMessage(i);
        }
    }

    @Test
    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("wier", context);
    }


}
