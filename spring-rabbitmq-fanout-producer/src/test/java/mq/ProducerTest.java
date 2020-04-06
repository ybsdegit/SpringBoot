package mq;

import com.ybs.ProducerApplication;
import com.ybs.mq.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * ProducerTest
 *
 * @author Paulson
 * @date 2020/4/6 23:47
 */
@SpringBootTest
@ContextConfiguration(classes = ProducerApplication.class)
public class ProducerTest {

    @Autowired
    private Producer producer;

    @Test
    void contextLoads() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            producer.sendMessage(i);
        }
    }
}
