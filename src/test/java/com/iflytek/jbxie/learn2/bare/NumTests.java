package com.iflytek.jbxie.learn2.bare;

/**
 * 数据除法
 *
 * @author jbxie
 * @create 2019/09/18 16:24
 */

public class NumTests {
    public static void main(String []args) {

        String num1 = "123.00";
//        System.out.println(StringUtils.split(num1, ".")[0]);

//        Float a = 5.67f;
//        System.out.println(a.intValue());
//        dataType();
//        Integer num1 = 4567;
//        System.out.println(BigDecimal.valueOf(Long.valueOf(num1)).divide(new BigDecimal(100)).setScale(2, RoundingMode.UP));
//        Integer data1 = 3;
//        System.out.println("调用data1");
//        data1(data1);
//        System.out.println("调用data1:" + data1);
//        System.out.println("data1 hashcode" + data1.hashCode());
//
//        int data2 = 3;
//        System.out.println("调用data2");
//        data2(data2);
//        System.out.println("调用data2:" + data2);

    }

//    public static void dataType() {
//        Integer integer1 = 3;
//        Integer integer2 = 3;
//        System.out.println(integer1 == integer2);
//        System.out.println(integer1.equals(integer2));
//
//        Integer integer3 = 125;
//        Integer integer4 = 125;
//        System.out.println(integer3 == integer4);
//        System.out.println(integer3.equals(integer4));
//
//        Integer integer5 = 129;
//        Integer integer6 = 129;
//        System.out.println(integer5 == integer6);
//        System.out.println(integer5.equals(integer6));
//
//    }

    public static void data1(Integer data) {
        data = 4;
        System.out.println("Integer 重新赋值");
        System.out.println(data);
        System.out.println("data hashcode" + data.hashCode());
    }

    public static void data2(int data) {
        data = 5;
        System.out.println("int 重新赋值");
        System.out.println(data);
    }
}
