package com.iflytek.jbxie.learn2.reflect;

import java.lang.reflect.Field;

/**
 * @author jbxie
 * @create 2021/02/22 20:27
 */

public class ReflectionFieldValueInfo {
    public static void main(String[] args) throws Exception {
/*        Object p = new Person("Xiao Ming");
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        // 调用Field.setAccessible(true)的意思是，别管这个字段是不是public，一律允许访问
        f.setAccessible(true);
        Object value = f.get(p);
        f.set(p, "Xiao Hong");
        System.out.println(value); // "Xiao Ming"*/

        Person p = new Person("Xiao Ming");
        System.out.println(p.getName()); // "Xiao Ming"
        Class c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        f.set(p, "Xiao Hong");
        System.out.println(p.getName()); // "Xiao Hong"
    }
    // 静态内部类（注意：没有外部静态类的概念
    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }
}
