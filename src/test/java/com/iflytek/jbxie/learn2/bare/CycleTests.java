package com.iflytek.jbxie.learn2.bare;

import java.util.Arrays;

/**
 * @author jbxie
 * @create 2020/06/05 16:15
 */

public class CycleTests {
    public static void main(String[] args) {
//        testFor();
        testForeach();
//        testForeach2();
    }
    public static void testFor() {
        for (int i = 1; i <= 5; i ++) {
            System.out.println("---for---num:" + i);
            if (i == 3) {
//                return;
                break;
            }
            System.out.println("---for---num:" + i);
        }
        System.out.println("***for***end");
    }

    public static void testForeach() {
        Integer [] arr = new Integer[] {1, 2, 3, 4, 5};
        Arrays.stream(arr).forEach(e -> {
            System.out.println("---forEach---num:" + e);
            if (e == 3) {
                return;
//                break; // 报错
            }
            System.out.println("---forEach---num:" + e);
        });
        System.out.println("***forEach***end");
    }

    public static void testForeach2() {
        Integer [] arr = new Integer[] {1, 2, 3, 4, 5};
        for (Integer num : arr) {
            System.out.println("---forEach2---num:" + num);
            if (num == 3) {
//                return;
                break;
            }
            System.out.println("---forEach2---num:" + num);
        }
        System.out.println("***forEach2***end");
    }
}
