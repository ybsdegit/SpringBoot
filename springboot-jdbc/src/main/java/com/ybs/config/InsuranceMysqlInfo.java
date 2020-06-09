package com.ybs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: DataSource
 * @Author Paulson
 * @Date 2020/6/9
 * @Description: res
 * driver-class-name: com.mysql.cj.jdbc.Driver
 * url: jdbc:mysql://192.168.2.133:3306/{database}?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai
 * username: root
 * password: mima
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "autotest.mysql")
public class InsuranceMysqlInfo {
    public String driverClassName;
    public String url;
    public String username;
    public String password;
}
