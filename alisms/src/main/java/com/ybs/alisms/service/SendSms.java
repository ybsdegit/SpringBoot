package com.ybs.alisms.service;

import java.util.Map;

/**
 * SendSms
 *
 * @author Paulson
 * @date 2020/5/3 22:19
 */
public interface SendSms {
    public boolean send(String phoneNumber, String templateCode, Map<String, Object> code);
    public boolean send(String phoneNumber, Map<String, Object> code);
}
