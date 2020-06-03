package com.ybs.mapper;

import com.ybs.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BlogMapper
 *
 * @author Paulson
 * @date 2020/6/4 0:22
 */

public interface BlogMapper extends JpaRepository<Blog, Long> {
}
