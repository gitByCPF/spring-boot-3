package com.cpf.boot.bean;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/2
 * Description: 实体类
 */
public class Cat {
    private String name;
    private Integer age;

    public Cat() {
    }

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
