package com.iflytek.jbxie.learn2.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用（适合做缓存）
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[10*1024*1024]);
        // m = null;
        System.out.print(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(m.get());
        // 在堆里面申请 11M 字节数组空间 ，这是装不下，系统会垃圾回收，先回收一次，如果不够的话，会把软引用干掉
        byte[] b = new byte[11*1024*1024];
        System.out.print(m.get());
    }

}
