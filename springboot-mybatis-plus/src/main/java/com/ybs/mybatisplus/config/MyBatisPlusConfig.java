package com.ybs.mybatisplus.config;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisPlusConfig
 *
 * @author Paulson
 * @date 2020/3/21 20:29
 */

@MapperScan("com.ybs.mybatisplus.mapper")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {
    // 注册乐观锁插件

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    // 逻辑删除组件
    @Bean
    public LogicSqlInjector logicSqlInjector(){
        return new LogicSqlInjector();
    }

    @Bean
    @Profile({"dev","test"}) //设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(100); // 设置SQL执行的最大时间，如果超过了则不实行
        performanceInterceptor.setFormat(true); // 是否格式化
        return performanceInterceptor;
    }
}
