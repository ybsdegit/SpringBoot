package com.ybs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ybs.mapper.UserMapper;
import com.ybs.pojo.TestTable;
import com.ybs.pojo.User;
import com.ybs.service.PaulsonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringJpaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaulsonService paulsonService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void saveTest() {
        TestTable test = new TestTable();
        test.setCreated(LocalDateTime.now());
        test.setDescription("test");
        paulsonService.saveTest(test);
    }


    @Test
    void saveUser() {
        User user = new User();
        user.setEmail("abc@123.com");
        user.setEnabled(1);
        user.setNickname("test");
        user.setRegTime(LocalDateTime.now());
        paulsonService.saveUser(user);
    }


    @Test
    void findUser() {
        User user = paulsonService.findUserById(20);
        System.out.println(user);
    }

    @Test
    void deleteUser() {
        paulsonService.deleteUser("test", 1);
    }

    @Test
    void test1() {
        MultiValueMap<String, String> multiValueMap;

        List<User> all = userMapper.findAll();
        for (User user : all) {
            multiValueMap = new LinkedMultiValueMap<>();
            Map map = JSON.parseObject(JSON.toJSONString(user), Map.class);
            multiValueMap.setAll(map);
            System.out.println(multiValueMap.toSingleValueMap().toString());
        }
    }


    @Test
    void test2() {
        String url = "http://localhost:8080/header";
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(doPost(url, user));
        }
    }

    @Test
    void test4() {
        String url = "http://localhost:8080/header";
        List<User> all = userMapper.findAll();
        for (User user : all) {
            String userStr = JSON.toJSONString(user);
            Map map = JSON.parseObject(userStr, Map.class);
            // 删除无用字段 或者构造请求头map
            map.remove("id");
            map.remove("enabled");
            map.remove("userface");
            JSONObject jsonObject = doPost(url, map, userStr);
            System.out.println(jsonObject);
        }
    }

    /**
     * private Headers headers;
     *
     */

    /**
     * post json header
     *
     * @param url
     * @param headerMap
     * @param body
     * @return
     */
    public JSONObject doPost(String url, Map headerMap, String body) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(headerMap);
        // 设置请求头
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.addAll(multiValueMap);
        System.out.println(headers.toSingleValueMap().toString());
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(url, request, JSONObject.class);
    }


    /**
     * 直接发送 Object （任意类型）的Bean，默认是 post json
     *
     * @param url
     * @param body
     * @return
     */
    public JSONObject doPost(String url, Object body) {
        return restTemplate.postForObject(url, body, JSONObject.class);
    }

    @Test
    void test3() {
        User user = userMapper.findById(21).orElse(null);
        Map<String, Object> map = beanToMap(user);
        System.out.println(map);
    }


    /**
     * bean 转 map 忽略空值
     * 这里使用 fastjson, 先将bean转json, 再将json转map。
     * JSON.toJSONString(objcet,SerializerFeature.WriteMapNullValue) 保留空值
     *
     * @param object
     * @return
     */
    public Map<String, Object> beanToMap(Object object) {
        return JSON.parseObject(JSON.toJSONString(object), Map.class);
    }

}
