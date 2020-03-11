package com.ybs.vueserver.service;

import com.ybs.vueserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/10 21:41
 */
public interface UserService extends JpaRepository<User, Integer> {
}
