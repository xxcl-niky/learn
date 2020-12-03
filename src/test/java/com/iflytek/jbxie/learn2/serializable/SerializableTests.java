package com.iflytek.jbxie.learn2.serializable;

import java.io.*;

/**
 * 序列化
 *
 * 文档可参考：https://mp.weixin.qq.com/s?__biz=MzIyNDU2ODA4OQ==&mid=2247484384&idx=1&sn=08034cd5b10b135cd7ac6946d415ce13&chksm=e80db596df7a3c8069183899d486c4bb48d201daf35c69b8c6d05b2cf211cc8bab4617eb22c6&scene=21#wechat_redirect
 * @author jbxie
 * @create 2020/09/13 11:56
 */

public class SerializableTests {
    private static Byte[] t;

    public static void main(String[] args) throws Exception{
        serializableToFile();
//        getValueOfSerializableFile();
    }

    public static void serializableToMemory() {
        // 定义输出

        // 定义输入
    }

    /**
     * 被transient关键字修饰的属性不会被序列化, static属性也不会被序列化.
     * @throws Exception
     */
    public static void serializableToFile() throws Exception{
        String fileName = "./s1.txt";
        // 定义输出
        Father father = new Father();
        father.setName("张三");
        father.setAge(45);
        father.setSex("man");
        ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutput.writeObject(father);
        getValueOfSerializableFile();
    }

    public static void getValueOfSerializableFile() throws Exception{
        String fileName = "./s1.txt";
        // 定义输入
        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileName));
        Father father1 = (Father) objectInput.readObject();
        System.out.println(father1);
    }
}
