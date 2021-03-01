package com.iflytek.jbxie.learn2.reflect;

import java.lang.reflect.Method;

/**
 * @author jbxie
 * @create 2021/02/26 9:16
 */

public class ReflectionMethodInvokeNoPublicInfo {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Method m = p.getClass().getDeclaredMethod("setName", String.class);
        m.setAccessible(true);
        m.invoke(p, "Bob");
        System.out.println(p.name);
    }

    static class Person {
        String name;
        private void setName(String name) {
            this.name = name;
        }
    }
}
