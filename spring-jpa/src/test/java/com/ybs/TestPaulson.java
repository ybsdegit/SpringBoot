package com.ybs;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * TestPaulson
 *
 * @author Paulson
 * @date 2020/6/4 0:47
 */

public class TestPaulson {

    @Test
    void test() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString());
    }
}
