package com.ybs.controller;

import com.ybs.service.SendSms;
import com.ybs.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * SmsApiController
 *
 * @author Paulson
 * @date 2020/5/3 22:25
 */

@RestController
// 跨域
@CrossOrigin
public class SmsApiController {

    @Value("${ali.templateCode}")
    private String templateCode;

    @Autowired
    private SendSms sendSms;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/send/{phone}")
    public String code(@PathVariable String phone) {

        Object code = redisUtil.get(phone);
        if (!StringUtils.isEmpty(code)) {
            return phone + ":" + code + "已存在，还没有过期";
        }
        // 生成验证码并存储到redis中
        code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("checkcode", code);
        boolean isSend = sendSms.send(phone, param);
        if (isSend) {
            redisUtil.set(phone, code, 60);
            return phone + ": " + code + "发送成功！";
        }else {
            return phone + ": " + code + "发送失败！";
        }
    }

    @GetMapping("/redis/send/{phone}")
    public String redisSms(@PathVariable String phone) {
        String code = "redis";
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("checkcode", code);
        boolean isSend = sendSms.send(phone, param);
        if (isSend) {
            redisUtil.set(phone, code, 60);
            return phone + ": " + code + "发送成功！";
        }else {
            return phone + ": " + code + "发送失败！";
        }

    }
}
