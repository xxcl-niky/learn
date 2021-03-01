package com.iflytek.jbxie.learn2.reflect;

import java.lang.reflect.Method;

/**
 * @author jbxie
 * @create 2021/02/26 11:03
 */

public class ReflectionMethodPolymorphicInfo {
    public static void main(String[] args) throws Exception {
        // 获取Person的hello方法:
        Method h = Person.class.getMethod("hello");
        // 对Student实例调用hello方法:
        h.invoke(new Student());
    }

    static class Person {
        public void hello() {
            System.out.println("Person:hello");
        }
    }
    static class Student extends Person {
        public void hello() {
            System.out.println("Student:hello");
        }
    }
}
