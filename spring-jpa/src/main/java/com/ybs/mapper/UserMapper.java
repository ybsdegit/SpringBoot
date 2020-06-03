package com.ybs.mapper;

import com.ybs.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * UserMapper
 *
 * @author Paulson
 * @date 2020/6/4 0:19
 */
public interface UserMapper extends JpaRepository<User, Integer> {

    @Transactional
    void deleteByNicknameAndEnabled(String nickname, Integer enabled);


}
