package com.ybs.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybs.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @author Paulson
 * @date 2020/3/21 16:48
 */

// 在对应的mapper上面实现基本的接口 BaseMapper

@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {
}
