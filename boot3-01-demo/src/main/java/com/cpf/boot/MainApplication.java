package com.cpf.boot;

import com.cpf.boot.beans.Child;
import com.cpf.boot.properties.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApplication.class, args);
        // 获取springboot项目的上下文对象
        Person person = ioc.getBean(Person.class);
        Child child = person.getChild();
        child.getHobbies().forEach(System.out::println);
        person.getDogs().forEach(dog -> System.out.println(dog.getName() + " " + dog.getAge()));
        person.getCats().forEach((k, cat) ->System.out.printf("%s{name: %s, age: %d}%n", k, cat.getName(), cat.getAge()));
    }
}
