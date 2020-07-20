package com.iflytek.jbxie.learn2.bare;

import java.util.function.Supplier;

/**
 * @author jbxie
 * @create 2020/05/22 14:57
 * https://www.cnblogs.com/runningTurtle/p/7092632.html
 * 什么是函数式接口（Functional Interface）
 * 函数式接口用途
 * 关于@FunctionalInterface注解
 * 函数式接口里允许定义默认方法
 * 函数式接口里允许定义静态方法
 * 函数式接口里允许定义java.lang.Object里的public方法
 * JDK中的函数式接口举例
 */
@FunctionalInterface
public interface FunctionInterfaceTests<T> {
    void access(T val);

    static Integer create(Supplier<Integer> supplier) {
        return supplier.get();
    }

}
