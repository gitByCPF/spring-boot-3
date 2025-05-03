package com.cpf.boot.config;

import com.cpf.boot.bean.Cat;
import com.cpf.boot.bean.Dog;
import com.cpf.boot.bean.User;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/2
 * Description: 配置类
 */
@SpringBootConfiguration
public class AppConfig {

    @ConditionalOnClass(name = "com.alibaba.druid.FastsqlException")
    @Bean(name = "cat01")
    public Cat cat() {
        return new Cat("小白", 2);
    }

    @ConditionalOnMissingClass(value = "com.alibaba.druid.FastsqlException")
    @Bean(name = "dog01")
    public Dog dog() {
        return new Dog("小黑", 3);
    }

    @ConditionalOnBean(name = "dog01")
    @Bean(name = "ZhangSan")
    public User user() {
        return new User("张三", 18);
    }
}
