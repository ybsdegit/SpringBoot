package com.ybs.dao.impl;

import com.ybs.dao.StudentDao;
import com.ybs.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ybsde
 */
@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增信息
     * @param student
     */
    @Override
    public void save(Student student) {
        mongoTemplate.save(student);
    }

    /**
     * 修改信息
     * @param student
     */
    @Override
    public void update(Student student) {
        //修改的条件
        Query query = new Query(Criteria.where("id").is(student.getId()));

        //修改的内容
        Update update = new Update();
        update.set("name",student.getName());

        mongoTemplate.updateFirst(query,update,Student.class);
    }

    /**
     * 查询所有信息
     * @return
     */
    @Override
    public List<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    /**
     * 根据id查询所有信息
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Student byId = mongoTemplate.findById(1,Student.class);
        mongoTemplate.remove(byId);
    }

}
