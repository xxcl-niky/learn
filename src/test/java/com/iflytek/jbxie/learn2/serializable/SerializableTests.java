package com.iflytek.jbxie.learn2.serializable;

import java.io.*;

/**
 * 序列化
 *
 * @author jbxie
 * @create 2020/09/13 11:56
 */

public class SerializableTests {
    private static Byte[] t;

    public static void main(String[] args) throws Exception{
//        serializableToFile();
        getValueOfSerializableFile();
    }

    public static void serializableToMemory() {
        // 定义输出

        // 定义输入
    }

    public static void serializableToFile() throws Exception{
        String fileName = "./s.txt";
        // 定义输出
        Father father = new Father();
        father.setName("张三");
        father.setAge(45);
        ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(fileName));
        objectOutput.writeObject(father);
        getValueOfSerializableFile();
    }

    public static void getValueOfSerializableFile() throws Exception{
        String fileName = "./s.txt";
        // 定义输入
        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(fileName));
        Father father1 = (Father) objectInput.readObject();
        System.out.println(father1.getName());
        System.out.println(father1);
    }
}
