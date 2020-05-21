package com.iflytek.jbxie.learn2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 相等比较
 *
 * @author jbxie
 * @create 2019/08/21 9:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqualComparisonTests {

    @Test
    public void constEqualTest() {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = "abcd";
        String s4 = new String("abc");
        String s5 = new String("abc");
        String s6 = new String("abcd");
        System.out.println("s1 == s2: " + ( s1 == s2));
        System.out.println("s1 equal s2: " + (s1.equals(s2)));
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s4 == s5: " + (s4 == s5));
        System.out.println("s4 equal s5: " + (s4.equals(s5)));
        System.out.println("s4 == s6: " + (s4 == s6));
    }

    @Test
    public void objectEqualTest() {

    }
}
