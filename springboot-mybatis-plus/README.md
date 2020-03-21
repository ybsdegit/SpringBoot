
### 创建数据库
1. 创建数据库和表
```
CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);

```
2. 插入数据
```
INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```


### mybatis-plus 快速启动
1. 导入依赖
```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
```
说明，使用 mybatis-plus 可以省略大量的代码，尽量不要同时导入 mybatis 和 mybatis-plus

2. 连接数据库 和 mybatis 相同
```
spring:
  datasource:
    username: root
    password: mima
    url: jdbc:mysql://192.168.2.136:3306/mybatis-plus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8
    driver-class-name: com.mysql.cj.jdbc.Driver
```

3. ==传统方式 pojo-dao （连接mybatis，配置mapper.xml文件）-service-controller ==
3. 使用mybatis-plus之后
    - pojo
    ```java
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {
        private Long id;
        private String name;
        private Integer age;
        private String email;
    }
    ```
    - mapper 接口
    ```java
    @Repository // 代表持久层
    public interface UserMapper extends BaseMapper<User> {
    }
    ```
    - 注意点 我们需要在主启动类上去扫描我们的mapper包小的所有接口 `@MapperScan("com.ybs.mybatisplus.mapper")`
    - 测试类中测试
    ```java
    @Autowired
        private UserMapper userMapper;

        @Test
        void contextLoads() {
            // 参数是一个Wrapper条件构造器
            //查询心全部用户
            List<User> users = userMapper.selectList(null);
            users.forEach(System.out::println);
        }
    ```
    - 结果
    ```
    User(id=1, name=Jone, age=18, email=test1@baomidou.com)
    User(id=2, name=Jack, age=20, email=test2@baomidou.com)
    User(id=3, name=Tom, age=28, email=test3@baomidou.com)
    User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
    User(id=5, name=Billie, age=24, email=test5@baomidou.com)
    ```

