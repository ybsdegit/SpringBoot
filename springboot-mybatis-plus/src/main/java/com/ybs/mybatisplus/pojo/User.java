package com.ybs.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * User
 *
 * @author Paulson
 * @date 2020/3/21 16:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 对应数据库的主键（uuid， 自增id， 雪花算法， redis）
    // 默认方案，全局唯一ID
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version // 乐观锁
    private Integer version;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
