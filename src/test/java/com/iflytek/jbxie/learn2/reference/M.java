package com.iflytek.jbxie.learn2.reference;

public class M {
    @Override
    // gc回收时会执行该方法
    protected void finalize() throws Throwable {
        System.out.print("在堆内存中 M对象被回收！");
    }
}
