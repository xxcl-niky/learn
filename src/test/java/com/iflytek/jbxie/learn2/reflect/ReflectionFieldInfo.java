package com.iflytek.jbxie.learn2.reflect;

/**
 * @author jbxie
 * @create 2021/02/22 20:12
 */

public  class ReflectionFieldInfo {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));
    }
    class Student extends Person {
        public int score;
        private int grade;
    }
    class Person {
        public String name;
    }
}
