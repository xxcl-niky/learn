package com.iflytek.jbxie.learn2.reflect;

import java.lang.reflect.Method;

/**
 * @author jbxie
 * @create 2021/02/25 19:31
 */

public class ReflectionMethodInvokeInfo {
    public static void main(String[] args) throws Exception {
        // String对象:
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method m = String.class.getMethod("substring", int.class, int.class);
        // 在s对象上调用该方法并获取结果:
        String r = (String) m.invoke(s, 6, 8);
        // 打印调用结果:
        System.out.println(r);
    }
}
