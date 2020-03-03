package com.ybs.mapper;

import com.ybs.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @author Paulson
 * @date 2020/3/3 0:18
 */
@Repository
@Mapper
public interface UserMapper {

    public User queryUserByName(String name);
}
