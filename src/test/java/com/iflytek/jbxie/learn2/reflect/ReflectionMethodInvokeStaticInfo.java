package com.iflytek.jbxie.learn2.reflect;

import java.lang.reflect.Method;

/**
 * @author jbxie
 * @create 2021/02/26 9:05
 */

public class ReflectionMethodInvokeStaticInfo {
    public static void main(String[] args) throws Exception {
        // 获取Integer.parseInt(String)方法，参数为String:
        Method m = Integer.class.getMethod("parseInt", String.class);
        // 调用该静态方法并获取结果:
        Integer n = (Integer) m.invoke(null, "12345");
        // 打印调用结果:
        System.out.println(n);
    }
}
