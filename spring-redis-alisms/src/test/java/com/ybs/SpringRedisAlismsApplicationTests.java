package com.ybs;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ybs.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringRedisAlismsApplicationTests {

//    @Test
    void contextLoads() {
    }

//    @Test
    void sendSms(){

//        private static String AccessKeyId;
//        private static String AccessKeySecret;
//        private static String signName;
//        private static String templateCode;

        String telPhone = "18810911636";
        String signName = "鲍森问答论坛";
        String templateCode = "SMS_181866518";
        String code = "2233";

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GGUtAc84qYpfHfVeWjY", "xySc8SZKTtt7aZGFIJEDjnIOXKudgj");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        // 自定义的参数
        request.putQueryParameter("PhoneNumbers", telPhone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);

        Map<String, Object> map = new HashMap<>();
        map.put("checkcode", code);
        request.putQueryParameter("TemplateParam", JSON.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

//    @Autowired
    private RedisUtil redisUtil;

//    @Test
    void testRedis(){
        redisUtil.set("18810911636", 2345, 60);
    }

//    @Test
    void testRedis2(){
        String code = redisUtil.get("18810911636").toString();
        System.out.println(code);
    }



}
