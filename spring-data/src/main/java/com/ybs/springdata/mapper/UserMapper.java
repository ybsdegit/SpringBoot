package com.ybs.springdata.mapper;

import com.ybs.springdata.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author Paulson
 * @date 2020/2/26 0:03
 */

// 这个注解表示了这是一个mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {

    //选择全部用户
    List<User> selectUser();
    //根据id选择用户
    User selectUserById(int id);
    //添加一个用户
    int addUser(User user);
    //修改一个用户
    int updateUser(User user);
    //根据id删除用户
    int deleteUser(int id);

}
