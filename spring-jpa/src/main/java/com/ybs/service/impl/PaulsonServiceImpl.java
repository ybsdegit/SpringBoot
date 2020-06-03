package com.ybs.service.impl;

import com.ybs.mapper.TestMapper;
import com.ybs.mapper.UserMapper;
import com.ybs.pojo.TestTable;
import com.ybs.pojo.User;
import com.ybs.service.PaulsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PaulsonServiceImpl
 *
 * @author Paulson
 * @date 2020/6/4 0:30
 */
@Service
public class PaulsonServiceImpl implements PaulsonService {

    @Autowired
    private TestMapper testMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveTest(TestTable test) {
        testMapper.save(test);
    }

    @Override
    public void saveUser(User user) {
        userMapper.save(user);
    }


    @Override
    public User findUserById(Integer id) {
        Optional<User> optionalUser = userMapper.findById(id);
        User user = optionalUser.get();
        return user;
    }

    @Override
    public void deleteUser(String nickname, Integer enabled) {
        userMapper.deleteByNicknameAndEnabled(nickname, enabled);
    }
}
