package com.ybs.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * ScheduleService
 *
 * @author Paulson
 * @date 2020/3/3 23:09
 */

@Service
public class ScheduleService {

    // 在一个特定的时间执行这个方法
    // cron 表达式
    // 秒 分 时 日 月 周几
    // 30 0/5 10,18 * * ? 每天10点和18点，每5分钟执行一次
    // , 枚举
    // - 区间
    // / 步长
    @Scheduled(cron = "0/5 * * * * 0-7")
    public void hello() {
        System.out.println("hello, 你被执行了~");
    }
}
