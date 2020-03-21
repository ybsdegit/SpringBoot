package com.ybs.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybs.mybatisplus.mapper.UserMapper;
import com.ybs.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * WrapperTest
 *
 * @author Paulson
 * @date 2020/3/21 22:03
 */

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testWrapper1(){
        // 查询 name 不为空，邮箱不为空，年龄大于22
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void test2(){
        // 查询 name 为 Sandy
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "Sandy");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test3(){
        // 年龄在20-30之间
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(wrapper);// 查询结果数目
        System.out.println(count);

    }

    @Test
    void test4(){
        // 模糊查询：name 不包含 E 的, email t开头
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 左和右  t%
        wrapper.notLike("name", "P")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    void test5(){
        // 内查询 IN
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // id 在子查询中查出来
        wrapper.inSql("id", "select id from user where id < 3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }


    @Test
    void test6(){
        // 排序 通过id降序排序
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    @Test
    void test(){

    }


}
