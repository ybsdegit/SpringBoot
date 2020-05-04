package com.ybs.alisms.controller;

import com.ybs.alisms.service.SendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

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

    @GetMapping("/send/{phone}")
    public String code(@PathVariable String phone) {
        String code = UUID.randomUUID().toString().substring(0, 4);
        HashMap<String, Object> param = new HashMap<>(1);
        param.put("checkcode", code);
        boolean isSend = sendSms.send(phone, param);
        if (isSend) {
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
            return phone + ": " + code + "发送成功！";
        }else {
            return phone + ": " + code + "发送失败！";
        }

    }
}
