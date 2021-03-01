package com.iflytek.jbxie.learn2.reflect;

/**
 * @author jbxie
 * @create 2021/02/28 16:03
 */

public class ReflectionInterfaceInfo {
    public static void main(String[] args) {
        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class i : is) {
            System.out.println(i);
        }
        // 获取父类实现的接口
        Class s2 = Integer.class.getSuperclass();
        Class[] is2 = s2.getInterfaces();
        for (Class i2 : is2) {
            System.out.println(i2);
        }
    }
}
