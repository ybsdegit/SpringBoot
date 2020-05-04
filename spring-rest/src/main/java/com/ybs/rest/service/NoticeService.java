package com.ybs.rest.service;

import com.ybs.rest.pojo.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * NoticeService
 *
 * @author Paulson
 * @date 2020/4/29 14:38
 */

@Service
public class NoticeService {

    @Autowired
    private RestTemplate restTemplate;
    private static final String URL = "http://gturnquist-quoters.cfapps.io/api/random";

    public void getNotice(){
        Quote notice = restTemplate.getForObject(URL, Quote.class);
        System.out.println(notice);
    }
}
