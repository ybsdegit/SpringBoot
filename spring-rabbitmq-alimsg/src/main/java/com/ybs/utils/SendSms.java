package com.ybs.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class SendSms {


    private static String AccessKeyId;
    private static String AccessKeySecret;
    private static String signName;
    private static String templateCode;

    @Value("${ali.AccessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        AccessKeyId = accessKeyId;
    }

    @Value("${ali.AccessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        AccessKeySecret = accessKeySecret;
    }

    @Value("${ali.signName}")
    public void setSignName(String signName) {
        SendSms.signName = signName;
    }

    @Value("${ali.templateCode}")
    public void setTemplateCode(String templateCode) {
        SendSms.templateCode = templateCode;
    }

    
    public static boolean sendCode(String telPhone, String code){
        DefaultProfile profile = DefaultProfile.getProfile("default", AccessKeyId, AccessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", telPhone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"checkcode\":"+ code +"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            if (response.getData()!=null && response.getHttpStatus() == 200 ){
                System.out.println(response.getData());
                return true;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static String sendCode(String telPhone){
        String code = getMsgCode();
        DefaultProfile profile = DefaultProfile.getProfile("default", AccessKeyId, AccessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", telPhone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"checkcode\":"+ code +"}");

        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject res = JSONObject.parseObject(response.getData());
            System.out.println(response.getData());
            if (response.getData()!=null && "OK".equals(res.getString("Message")) ){
                return code;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    }


    public static void main(String[] args) {
        sendCode("18810911636", "123456");
    }
}
