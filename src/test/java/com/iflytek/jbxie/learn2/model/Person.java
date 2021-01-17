package com.iflytek.jbxie.learn2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 人
 *
 * @author jbxie
 * @create 2019/08/19 20:57
 */
@Data
@AllArgsConstructor
public class Person {
    static {
        System.out.println("static is start");
    }
    private String name;
    private String pwd;
    private Integer age;
}
