package com.ybs.mapper;

import com.ybs.pojo.TestTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TestMapper
 *
 * @author Paulson
 * @date 2020/6/4 0:25
 */
public interface TestMapper extends JpaRepository<TestTable, Long> {
}
