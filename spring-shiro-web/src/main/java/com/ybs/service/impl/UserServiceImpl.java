package com.ybs.service.impl;

import com.ybs.mapper.UserMapper;
import com.ybs.pojo.User;
import com.ybs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author Paulson
 * @date 2020/3/3 0:22
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
