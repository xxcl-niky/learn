package com.iflytek.jbxie.learn2.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * @author jbxie
 * @create 2021/01/27 16:02
 */

public class UDPServerSocketTests {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(6666); // 监听指定端口
        for (;;) { // 无限循环
            // 数据缓冲区:
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet); // 收取一个UDP数据包
            // 收取到的数据存储在buffer中，由packet.getOffset(), packet.getLength()指定起始位置和长度
            // 将其按UTF-8编码转换为String:
            String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
            System.out.println("req:" + s);
            // 发送数据:
            byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            ds.send(packet);
        }
    }
}
