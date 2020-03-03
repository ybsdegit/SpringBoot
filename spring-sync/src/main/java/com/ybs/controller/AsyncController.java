package com.ybs.controller;

import com.ybs.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AsyncController
 *
 * @author Paulson
 * @date 2020/3/3 22:21
 */

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping({"/","/hello"})
    public String hello(){
        asyncService.hello();
        return "OK";
    }
}
