package com.cpf.logging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/12
 * Description: 测试日志
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "hello";
    }
}
