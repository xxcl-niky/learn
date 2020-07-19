package com.iflytek.jbxie.learn2.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference m = new WeakReference(new M());
        System.out.print(m.get());
        System.gc();
//        try {
//            Thread.sleep(500);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.print(m.get());
    }
}
