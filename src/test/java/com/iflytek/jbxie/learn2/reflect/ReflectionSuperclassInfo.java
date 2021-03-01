package com.iflytek.jbxie.learn2.reflect;

/**
 * @author jbxie
 * @create 2021/02/26 11:33
 */

public class ReflectionSuperclassInfo {
    public static void main(String[] args) {
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);
        Class o = n.getSuperclass();
        System.out.println(o);
        System.out.println(o.getSuperclass());
    }
}
