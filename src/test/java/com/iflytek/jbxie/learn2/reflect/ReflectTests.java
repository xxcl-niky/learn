package com.iflytek.jbxie.learn2.reflect;

/**
 * 发射测试
 *
 * @author jbxie
 * @create 2020/09/18 17:27
 */

public class ReflectTests{
    public static void main(String[] args) {
        Person person = new Person();
        person.setFirstName("zhang");
        person.setLastName("san");
        System.out.println(getFullName(person));
    }

    public static String getFullName(Person person) {
        return person.getFirstName() + " " + person.getLastName();
    }

    /**
     *
     * @param object
     * @return
     */
    public static String getFullName(Object object) {
        int i = 0;
        Integer J = 0;
        return null;
    }


    public static void classTest() {
    }
}
