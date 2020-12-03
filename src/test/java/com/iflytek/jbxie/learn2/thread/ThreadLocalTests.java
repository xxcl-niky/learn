package com.iflytek.jbxie.learn2.thread;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * 本地线程测试
 * threadLocal: https://www.jianshu.com/p/ee8c9dccc953
 * @author jbxie
 * @create 2020/05/22 9:10
 */

public class ThreadLocalTests {

    private static int a = 0;
/*    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/
    public static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> {
        Integer i = 0;
        return i;
    });

    public static void main(String[] args) {
/*        Thread [] threads = new Thread[5];
        for (int i=0; i<threads.length; i++) {
            threads[i] = new Thread(() -> {
//                a += 5;
                int x = threadLocal.get().intValue();
                x += 5;
                threadLocal.set(x);
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get().intValue());
            }, "thread" + i);
        }
        Arrays.stream(threads).forEach(
                (e) -> {
                    e.start();
                }
        );*/

        joinTest();
    }


    private static void joinTest(){
        try {
            Thread t = new Thread(new Runnable()
            {
                public void run()
                {
                    System.out.println("First task started");
                    System.out.println("Sleeping for 2 seconds");
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("First task completed");
                }
            });
            Thread t1 = new Thread(new Runnable()
            {
                public void run()
                {
                    System.out.println("Second task completed");
                }
            });
            t.start(); // Line 15
            t.join(); // Line 16
            t1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
