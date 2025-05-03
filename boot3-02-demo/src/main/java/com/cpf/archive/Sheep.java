package com.cpf.archive;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/2
 * Description: @EnableConfigurationProperties注解的使用，可以不必在乎配置类的位置，更加解耦
 */
@ConfigurationProperties(prefix = "sheep")
public class Sheep {
    private Long id;
    private String name;
    private int age;

    public Sheep() {
    }

    public Sheep(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
