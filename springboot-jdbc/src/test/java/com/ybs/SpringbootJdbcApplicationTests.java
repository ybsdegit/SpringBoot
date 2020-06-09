package com.ybs;

import com.ybs.config.InsuranceMysqlInfo;
import com.ybs.util.JdbcUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.Map;

@SpringBootTest
class SpringbootJdbcApplicationTests {

    @Autowired
    private InsuranceMysqlInfo insuranceMysqlInfo;


    @Test
    void contextLoads() {
        System.out.println(insuranceMysqlInfo.url);
        System.out.println(insuranceMysqlInfo.driverClassName);
    }

    @Test
    void test() throws SQLException {
        JdbcUtils jdbcUtil = new JdbcUtils(insuranceMysqlInfo);
        Map<String, Object> paulson = jdbcUtil.excute("paulson", "SELECT * from user where id = 2");
        System.out.println(paulson.get("nickname"));
    }

    @Test
    void test1() throws SQLException {
        JdbcUtils jdbcUtil = new JdbcUtils(insuranceMysqlInfo);
        Map<String, Object> paulson = jdbcUtil.excute("paulson", "SELECT * from user where nickname = ? and username = ?", "元宝dsfdfsfdssdf森3", "魏元宝");
        System.out.println(paulson.get("nickname"));
    }


    @Test
    void testdelete() throws SQLException {
        JdbcUtils jdbcUtil = new JdbcUtils(insuranceMysqlInfo);
        Boolean aBoolean = jdbcUtil.excuteDelete("paulson", "DELETE from user where password = ? and nickname = ?", "mima", "adfdfasfdas");
        System.out.println(aBoolean);
    }


}
