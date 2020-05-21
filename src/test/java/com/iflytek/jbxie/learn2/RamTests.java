package com.iflytek.jbxie.learn2;

import com.iflytek.jbxie.learn2.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * 内存
 *
 * @author jbxie
 * @create 2019/08/20 22:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RamTests {

    @Test
    public void setTest() {
        Set<Person> set = new HashSet<Person>();
        Person p1 = new Person("zhangsan1", "pwd1", 10);
        Person p2 = new Person("zhangsan2", "pwd2", 20);
        Person p3 = new Person("zhangsan3", "pwd3", 30);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("总共有：" + set.size() + "个元素"); // 3个元素
//        p3.setAge(33);
//        set.remove(p3);
        set.add(p3);
        System.out.println("总共有：" + set.size() + "个元素"); // 3个元素
        for (Person person : set) {
            System.out.println(person);
        }
    }

}
