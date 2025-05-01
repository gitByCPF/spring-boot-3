package com.cpf.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/4/30
 * Description: 第一个请求处理器
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot3!";
    }
}
