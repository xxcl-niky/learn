package com.iflytek.jbxie.learn2.reflect;

/**
 * @author jbxie
 * @create 2021/02/25 19:19
 */

public class ReflectionMethodInfo {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));
    }
    static class Student extends Person {
        public int getScore(String type) {
            return 99;
        }
        private int getGrade(int year) {
            return 1;
        }
    }
    static class Person {
        public String getName() {
            return "Person";
        }
    }
}
