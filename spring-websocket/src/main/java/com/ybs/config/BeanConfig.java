package com.ybs.config;

import com.ybs.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName: BeanConfig
 * @Author Paulson
 * @Date 2020/12/5
 * @Description:
 */

@Configuration
public class BeanConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 雪花算法 生成唯一ID
     *
     * @return long
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
