package com.ybs.rest;

import com.alibaba.fastjson.JSONObject;
import com.ybs.rest.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SpringRestApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NoticeService noticeService;

    @Test
    void test() {
        noticeService.getNotice();
    }

    @Test
    void test2() {
        String url = "http://localhost:8080/header";
        HttpHeaders headers = new HttpHeaders();
        // 添加额外http请求头参数
        // headers.add("token1", "123456");
        // headers.add("token2", "123456");
        // headers.add("token3", "123456");
        // headers.add("token4", "123456");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("token1", "123456");
        multiValueMap.add("token2", "123456");
        multiValueMap.add("token3", "123456");
        multiValueMap.add("token4", "123456");
        headers.addAll(multiValueMap);
        System.out.println(headers.toSingleValueMap().toString());


        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = new JSONObject();
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toJSONString(), headers);
        JSONObject result = restTemplate.postForObject(url, request, JSONObject.class);
        System.out.println(result);

    }

}
