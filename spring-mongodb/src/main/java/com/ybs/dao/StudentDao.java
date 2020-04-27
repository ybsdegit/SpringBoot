package com.ybs.dao;

import com.ybs.pojo.Student;

import java.util.List;

/**
 * StudentDao
 *
 * @author Paulson
 * @date 2020/4/27 22:07
 */
public interface StudentDao {


    void save(Student student);

    void update(Student student);

    List<Student> findAll();

    void delete(Integer id);
}
