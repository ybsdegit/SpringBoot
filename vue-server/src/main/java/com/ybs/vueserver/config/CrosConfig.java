package com.ybs.vueserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CrosConfig
 *
 * @author Paulson
 * @date 2020/3/10 22:02
 */

@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "UPDATE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
