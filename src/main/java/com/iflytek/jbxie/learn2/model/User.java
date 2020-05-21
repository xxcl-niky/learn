package com.iflytek.jbxie.learn2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户
 *
 * @author jbxie
 * @create 2020/03/04 17:07
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private String gender;
    private int age;
}
