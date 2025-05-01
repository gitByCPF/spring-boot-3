package com.cpf.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/4/30
 * Description: 启动springboot项目的主程序入口
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 启动springboot项目
        SpringApplication.run(MainApplication.class, args);
    }
}
