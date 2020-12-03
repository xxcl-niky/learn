package com.iflytek.jbxie.learn2.bare;

import java.time.LocalDateTime;

/**
 * 编码测试
 *
 * @author jbxie
 * @create 2019/09/22 11:12
 */

public class CodeTests {
    public static void main(String[]args) {
//        System.out.println(LocalDateTime.now());
//        charTest();
//        stringCharTest();
        t();
    }

    public static void charTest() {
        char c = 'a';
        System.out.println(c);
    }

    public static void stringCharTest() {
        String str1 = "jbxie";
        char c1 = 'a';
        System.out.println(str1);
        System.out.println(c1);
    }

    public static void t() {
        int [] t1 = {1 ,2 ,3};
        for (int temp : t1) {
            switch (temp) {
                case 1:
                case 2:
                    System.out.println("t");
                case 3:
                    System.out.println("tt");
                    break;
                default:
                    break;
            }
        }
    }
}
