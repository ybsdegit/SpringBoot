package com.ybs.util;

import com.ybs.config.InsuranceMysqlInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JdbcUtil
 * @Author Paulson
 * @Date 2020/6/9
 * @Description:
 */

// @Data
public class JdbcUtils {

    private InsuranceMysqlInfo insuranceMysqlInfo;
    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;
    Connection connection;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Map<String, Object>> list;


    public JdbcUtils(InsuranceMysqlInfo insuranceMysqlInfo) {
        this.insuranceMysqlInfo = insuranceMysqlInfo;
        driverClassName = insuranceMysqlInfo.driverClassName;
        url = insuranceMysqlInfo.url;
        username = insuranceMysqlInfo.username;
        password = insuranceMysqlInfo.password;
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 入口
     * jdbcUtil.excuteDelete("paulson", "DELETE from user where password = ? and nickname = ?", "mima", "adfdfasfdas");
     *
     * @param database 传入数据库
     * @param sqlStr
     */
    public Map<String, Object> excute(String database, String sqlStr, Object... args) throws SQLException {
        connection = getConnection(url.replace("{database}", database));
        return executeQueryOne(connection, sqlStr, args);
    }

    /**
     * "DELETE from user where password = ? and nickname = ?", "mima", "adfdfasfdas"
     *
     * @param database
     * @param sqlStr
     * @param args
     * @return
     * @throws SQLException
     */
    public Boolean excuteDelete(String database, String sqlStr, Object... args) throws SQLException {
        url = url.replace("{database}", database);
        System.out.println(url);
        connection = getConnection(url);
        return executeUpdate(connection, sqlStr, args);
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection(String url) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param conn
     * @param sql
     * @param args
     * @return
     * @throws SQLException
     */
    public Map<String, Object> executeQueryOne(Connection conn, String sql, Object... args) throws SQLException { //可变参数  Object... args\
        List<Map<String, Object>> results = executeQuery(connection, sql, args);
        if (results.size() != 1) {
            throw new SQLException("SqlError：期待一行返回值，却返回了太多行！");
        }
        return results.get(0);
    }

    /**
     * 增删改的通用方法
     *
     * @paramString sql  要执行的sql
     * @paramObject[] obj    对象类型的数组  里面存放着 sql执行的占位符参数
     * 【name，age，id】
     * 【id】
     * 【name，age】
     * Object... 可变参数
     */
    public boolean executeUpdate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            int i = ps.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            close(conn, ps, null);
        }
        return false;
    }

    public boolean executeUpdate(Connection conn, String sql, Object... args) {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            int i = pstmt.executeUpdate();

            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            close(conn, pstmt, null);
        }
        return false;
    }


    /**
     * c查询的通用方法
     *
     * @param sql
     * @param args
     * @return
     */
    public List<Map<String, Object>> executeQuery(Connection conn, String sql, Object... args) { //可变参数  Object... args
        list = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            rs = pstmt.executeQuery();

            int count = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();//一行数据 用一个map 接收
                for (int i = 0; i < count; i++) {
                    String name = rs.getMetaData().getColumnLabel(i + 1);
                    map.put(name, rs.getObject(name));
                }
                /*将每行的map存放到 List中*/
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return null;
    }

    /**
     * c查询的通用方法
     *
     * @param sql
     * @param args
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... args) { //可变参数  Object... args
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            /* 有可能有参数 */
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /*执行*/
            set = ps.executeQuery();
            /*需要将所有数据都存放到 List中    每一行 用一个 map存放*/
            List<Map<String, Object>> list = new ArrayList<>();
            /*获取本次查询结果集有多少列*/
            int count = set.getMetaData().getColumnCount();

            while (set.next()) {
                Map<String, Object> map = new HashMap<>();//一行数据 用一个map 接收
                /*
                我们不用在乎数据库表中有几列
                通过  getMetData().getColumnLabel() 获取列
                因为用的map键值对集合  得到了列  就能得到相应的values值
                 */
                for (int i = 0; i < count; i++) {
                    String name = set.getMetaData().getColumnLabel(i + 1);
                    map.put(name, set.getObject(name));
                }
                /*将每行的map存放到 List中*/
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, set);
        }
        return null;
    }

    /**
     * 关闭的通用方法
     * 先进后出的原则
     */
    private static void close(Connection conn, PreparedStatement st, ResultSet set) {
        try {
            if (set != null) {
                set.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
