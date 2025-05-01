package com.cpf.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/1
 * Description: 控制器测试
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }

}
