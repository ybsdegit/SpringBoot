package com.ybs.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Student
 *
 * @author Paulson
 * @date 2020/4/27 22:05
 */

@Data
@Document(collation = "student")
public class Student implements Serializable {
    @Id
    private Long id;

    private String name;

    private String sex;

    private String age;

    private String introduce;
}
