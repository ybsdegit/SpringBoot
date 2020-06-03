package com.ybs.service;

import com.ybs.pojo.TestTable;
import com.ybs.pojo.User;

/**
 * PaulsonService
 *
 * @author Paulson
 * @date 2020/6/4 0:29
 */


public interface PaulsonService {

    public void saveTest(TestTable test);

    public void saveUser(User user);

    public User findUserById(Integer id);

    public void deleteUser(String nickname, Integer enabled);

}
