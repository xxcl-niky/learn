package com.iflytek.jbxie.learn2.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用
 *
 * @author jbxie
 * @create 2020/07/20 0:02
 */

/**
 * 虚引用
 */
public class PhantomReferenceTest {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue QUEUE = new ReferenceQueue();
    public static void main(String[] args) {
        PhantomReference<M> m = new PhantomReference<>(new M(), QUEUE);
        System.out.println(m.get());

        new Thread(() -> {
                while (true) {
                    LIST.add(new byte[1*1024*1024]);
                    try {
                        Thread.sleep(1*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(m.get());
                }
            }
        ).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("-----虚引用对象被JVM回收了-----" + poll);
                }
            }
        }
        ).start();
    }
}
