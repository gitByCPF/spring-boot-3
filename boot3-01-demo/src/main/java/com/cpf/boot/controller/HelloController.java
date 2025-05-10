package com.cpf.boot.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/4/30
 * Description: 第一个请求处理器
 */
@RestController
public class HelloController {
    private final StringRedisTemplate stringRedisTemplate;

    public HelloController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot3!";
    }

    @GetMapping("/increment")
    public int increment() {
        stringRedisTemplate.opsForValue().increment("number", 1);
        return Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("number")));
    }
}
