package com.ybs.mybatisplus.config;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
}
