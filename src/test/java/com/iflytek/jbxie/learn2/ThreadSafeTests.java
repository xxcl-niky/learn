package com.iflytek.jbxie.learn2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 线程安全
 *
 * @author jbxie
 * @create 2019/08/27 8:51
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ThreadSafeTests {
    private int x = 0;

    public void countNum() {
        x++;
    }

//    @Test
    public void threadSafeTest() {
        // 线程A
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    countNum();
                }
                System.out.println("final x from A:" + x);
            }
        }.start();

        // 线程B
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    countNum();
                }
                System.out.println("final x from B:" + x);
            }
        }.start();
    }
    public static void main(String[] args) {
        new ThreadSafeTests().threadSafeTest();
    }
}
