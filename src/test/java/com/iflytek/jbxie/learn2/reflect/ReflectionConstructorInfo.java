package com.iflytek.jbxie.learn2.reflect;

import java.lang.reflect.Constructor;

/**
 * @author jbxie
 * @create 2021/02/26 11:14
 */

public class ReflectionConstructorInfo {
    public static void main(String[] args) throws Exception {
        // 获取构造方法Integer(int):
        Constructor cons1 = Integer.class.getConstructor(int.class);
        // 调用构造方法:
        Integer n1 = (Integer) cons1.newInstance(123);
        System.out.println(n1);

        // 获取构造方法Integer(String)
        Constructor cons2 = Integer.class.getConstructor(String.class);
        Integer n2 = (Integer) cons2.newInstance("456");
        System.out.println(n2);
    }
}
