package com.sxt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @program: fastdfs-demo
 * @author: 雷哥
 * @create: 2020-01-03 10:44
 **/

@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {

    private String baseUrl;

    private List<String> allowTypes;
}