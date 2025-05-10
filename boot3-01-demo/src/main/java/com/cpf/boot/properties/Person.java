package com.cpf.boot.properties;

import com.cpf.boot.beans.Cat;
import com.cpf.boot.beans.Child;
import com.cpf.boot.beans.Dog;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/10
 * Description: 演示properties对象与yml文件的对应关系，顺便学习yml格式细节
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private int age;
    private Date birthday;
    private boolean like;
    @NestedConfigurationProperty
    private Child child;
    private List<Dog> dogs;
    private Map<String, Cat> cats;
}
