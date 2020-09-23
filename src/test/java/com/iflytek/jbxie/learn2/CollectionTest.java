package com.iflytek.jbxie.learn2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 集合测试
 *
 * @author jbxie
 * @create 2019/08/14 19:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionTest {
    @Test
    public void ListTest() {
        String [] a1 = {"1", "2"};
        int [] a2 = {1, 2};
        int [] a3 = new int[]{1,2};
        int [] a4 = new int[2];
        a4[0] = 1;
        a4[1] = 2;

        // List: ArrayList, LinkedList, Vector都是List接口的实现(List继承Collection接口)
        List arrayList = new ArrayList();
        String name1 = "zhangsan1";
        String name2 = "zhangsan2";
        String name3 = "zhangsan3";
        arrayList.add(name1);
        arrayList.add(name2);
        arrayList.add(name3);
        arrayList.add(name3);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(1));
        arrayList.remove(1);
        System.out.println(arrayList);
        
        List linkedList = new LinkedList();
        List vector = new Vector();
    }

    @Test
    public void mapTest() {
        // Map: HashMap, HashMap都是Map接口的实现
       Map hashMap = new HashMap<>();
       Map hashtable = new Hashtable();
    }

    @Test
    public void setTest() {
        Set hashSet = new HashSet(); // HashSet:底层数据结构是哈希表(实际是HashMap实例)
    }

    @Test
    public void collectionsTest() {
        List<Integer> arrayList = new ArrayList();
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(3);
        System.out.println(Collections.max(arrayList));
        Collections.sort(arrayList);
        System.out.println(arrayList);
        Collections.sort(arrayList, new Comparator<Integer>() {
            /*
             * int compare(Integer o1, Integer o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2
             */
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                }
                if (o1 == o2) {
                    return 0;
                }
                return 1;
            }
        });
        System.out.println(arrayList);

        System.out.println(Collections.replaceAll(arrayList, 1, 11));
        System.out.println(arrayList);
    }

    @Test
    public void arraysTest() {
        String[] array = new String[10];
        array[0] = "lisi1";
        array[1] = "lisi2";
        array[2] = "lisi3";
        String[] array1 = new String[10];
        array1[0] = "lisi1";
        array1[1] = "lisi2";
        array1[2] = "lisi3";
        System.out.println(Arrays.asList(array));
        System.out.println(Arrays.equals(array, array1));
    }
}
