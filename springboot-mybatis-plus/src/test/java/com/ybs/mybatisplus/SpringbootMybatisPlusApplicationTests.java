package com.ybs.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybs.mybatisplus.mapper.UserMapper;
import com.ybs.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringbootMybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 参数是一个Wrapper条件构造器
        //查询心全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("Paulson 的插入");
        user.setAge(44);
        user.setEmail("135464564@qq.com");
        user.setId(6L);
        int result = userMapper.insert(user); // 自动生成ID
        System.out.println(user);
        System.out.println(result);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        // 通过条件自动拼接动态sql
        user.setId(6L);
        user.setAge(15);
        user.setName("Paulson 的更新");
        userMapper.updateById(user);
    }

    // 测试乐观锁，成功
    @Test
    public void testOptimisticLocker(){
        // 1、查询用户信息
        User user = userMapper.selectById(1L);
        // 2、修改用户信息
        user.setName("ybs");
        user.setEmail("YYYYYYYYY@YYY.com");
        // 3、执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁，失败，多线程
    @Test
    public void testOptimisticLocker2(){
        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("ybs111");
        user.setEmail("11111111@YYY.com");

        // 线程2 模拟另一个线程进行插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("ybs222");
        user2.setEmail("22222222@YYY.com");
        userMapper.updateById(user2);

        // 自旋锁多次尝试操作。多线程下一定要加锁
        // 3、执行更新操作
        userMapper.updateById(user);  // 如果没有乐观锁，就会覆盖插队线程的值
    }

    // 测试查询
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);

//        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
//        users.forEach(System.out::println);
    }

    // 条件查询  使用Map操作
    @Test
    public void testSelectByBatchIds(){
        HashMap<String, Object> map = new HashMap<>();
        // 自定义查询条件
        map.put("name", "Paulson 的测试");
        map.put("age", 43);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    // 测试分页插件
    @Test
    public void testPage(){
        // 参数一：当前页
        // 参数二：页面大小
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }


    // 测试删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(1L);
//        userMapper.deleteBatchIds(Arrays.asList(2, 3));
    }

    @Test
    public void testDeleteMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Paulson 的测试");
        userMapper.deleteByMap(map);
    }





}
