package com.ybs.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncService
 *
 * @author Paulson
 * @date 2020/3/3 22:19
 */

@Service
public class AsyncService {

    @Async
    public void hello(){
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据正在处理!");
    }
}
