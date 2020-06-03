package com.ybs.dao.impl;

import com.ybs.dao.AccountDao;

import java.util.List;

/**
 * AccoutDaoImpl
 *
 * @author Paulson
 * @date 2020/5/31 18:53
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public List findAll() {
        System.out.println("查询成功");
        return null;
    }
}
