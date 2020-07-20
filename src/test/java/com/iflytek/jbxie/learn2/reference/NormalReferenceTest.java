package com.iflytek.jbxie.learn2.reference;

/**
 * 强引用
 */
public class NormalReferenceTest {
    public static void main(String [] args) throws Exception{
        M m = new M();
        // 将m置为null：即将m地址指针去掉
        m = null;
        // 进行垃圾回收
        System.gc();
        System.out.print(m);
        Thread.sleep(10* 1000);
    }
}
