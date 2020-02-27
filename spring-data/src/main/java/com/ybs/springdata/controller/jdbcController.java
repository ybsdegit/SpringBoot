package com.ybs.springdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * jbbcController
 *
 * @author Paulson
 * @date 2020/2/25 23:09
 */

@RestController
public class jdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询数据库的所有信息
     */
    @GetMapping("/userList")
    public List<Map<String, Object>> userList(){
        String sql = "select * from t_user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into t_tag(id, name) values(1, '小明')";
        jdbcTemplate.update(sql);
        return "成功";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable int id){
        String sql = "update t_tag set name = ? where id = " + id;
        Object[] objects = new Object[2];
        objects[0] = "小明2";
        objects[1] = "小明3";
        jdbcTemplate.update(sql,objects[0]);
        return "成功";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        String sql = "delete from t_tag where id = ?";
        jdbcTemplate.update(sql, id);
        return "成功";
    }
}
