package com.iflytek.jbxie.learn2.guava;

import com.google.common.base.Joiner;
import org.assertj.core.util.Lists;

/**
 * guava字符串处理
 *
 * @author jbxie
 * @create 2020/02/14 15:58
 */

public class guavaStringTests {
    private static String t = "abc";
    private Integer t1 = 1;
    public static void main(String [] args) {
        stringHandleTest();
        System.out.println(t);
        System.out.println(StaticClass.a);
    }

    public static void stringHandleTest() {
        Joiner joiner = Joiner.on(",").skipNulls();
        String test = joiner.join(Lists.newArrayList("jbxie", null, "wanli"));
        System.out.println(test);
    }

    public static class StaticClass {
        private static int a = 1;
        private int b = 1;

        public void test() {
            a = 2;
            b = 3;
        }
    }
}
