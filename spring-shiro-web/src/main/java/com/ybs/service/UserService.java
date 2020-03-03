package com.ybs.service;

import com.ybs.pojo.User;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/3 0:22
 */
public interface UserService {

    public User queryUserByName(String name);

}
