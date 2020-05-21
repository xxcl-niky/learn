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
    public static void main(String [] args) {
        stringHandleTest();
    }

    public static void stringHandleTest() {
        Joiner joiner = Joiner.on(",").skipNulls();
        String test = joiner.join(Lists.newArrayList("jbxie", null, "wanli"));
        System.out.println(test);
    }
}
