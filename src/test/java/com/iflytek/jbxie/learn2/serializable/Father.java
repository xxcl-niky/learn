package com.iflytek.jbxie.learn2.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * 父亲
 *
 * @author jbxie
 * @create 2020/09/13 11:57
 */
@Data
public class Father implements Serializable {
    private String name;
    private int age;
}
