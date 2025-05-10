package com.cpf.boot.beans;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cui pengfei
 * @since 17
 * Date 2025/5/11
 * Description: properties对象中的子对象
 */
@Data
public class Child {
    private String name;
    private int age;
    private Date birthday;
    private List<String> hobbies;
}
