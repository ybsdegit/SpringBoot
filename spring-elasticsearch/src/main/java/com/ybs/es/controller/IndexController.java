package com.ybs.es.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 *
 * @author Paulson
 * @date 2020/4/9 22:32
 */

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }
}
