package com.ybs.springdata.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * DruidConfig
 *
 * @author Paulson
 * @date 2020/2/25 23:40
 */

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    // 后台监控功能
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 后台需要有人登陆，账号密码配置
        HashMap<String, String> initParams = new HashMap<>();
        // 增加配置
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "mima");

        // 允许谁可以访问
        initParams.put("allow", "");

        // 禁止谁能访问
//        initParams.put("kuangsheng", "192.168.1.999");

        bean.setInitParameters(initParams); // 设置初始化参数
        return bean;
    }

    //
}
