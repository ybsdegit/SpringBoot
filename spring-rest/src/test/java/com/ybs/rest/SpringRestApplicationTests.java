package com.ybs.rest;

import com.ybs.rest.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private NoticeService noticeService;

    @Test
    void test(){
        noticeService.getNotice();
    }

}
