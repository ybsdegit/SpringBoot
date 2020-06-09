package com.ybs.controller;

import com.ybs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: PaulsonController
 * @Author Paulson
 * @Date 2020/6/7
 * @Description:
 */

@RestController
public class PaulsonController {

    @Autowired
    private UserMapper userMapper;





}
