package com.iflytek.jbxie.learn2.bare;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 排序
 *
 * @author jbxie
 * @create 2020/08/20 15:24
 */

public class SortTests {
    public static void main(String[] args) {
        sortedMapTest();
    }

    public static void sortedMapTest() {
        Map<String, String> map = new HashMap<>();
        map.put("A", "1");
        map.put("C", "2");
        map.put("B", "3");
        SortedMap<String, String> sortedMap = new TreeMap<>((o1, o2) -> -(o1.compareTo(o2)));
        sortedMap.putAll(map);
        for (Map.Entry<String, String> entry:sortedMap.entrySet()) {
            System.out.println(entry.getKey() +"="+ entry.getValue());
        }
//        Joiner.on("&").withKeyValueSeparator("=").join(sortedMap);
    }
}
