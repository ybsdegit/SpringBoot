package com.ybs.rest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName: RestController
 * @Author Paulson
 * @Date 2020/6/11
 * @Description:
 */
@RestController
@Slf4j
public class RestTestController {
    @PostMapping("/header")
    public JSONObject listAllHeaders(@RequestHeader Map<String, String> headers, @RequestBody Object object) {
        headers.forEach((key, value) -> {
            // 日志中输出所有请求头
            log.info(String.format("Header '%s' = %s", key, value));
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("header", JSON.toJSONString(headers));
        jsonObject.put("body", JSON.toJSONString(object));
        return jsonObject;
    }
}
