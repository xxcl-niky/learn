package com.iflytek.jbxie.learn2.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 素材标识socket
 *
 * @author jbxie
 * @create 2020/01/05 16:07
 */
@Component
@ServerEndpoint(value = "/material/{uKey}/{platform}")
public class MaterialSocket {

    /**
     * 静态变量，用来记录当前在线连接数（应该把它设计成线程安全的）
     */
    private static int onlineCount = 0;

    /**
     * concurrent包的线程安全map,用来存放每个客户端对应的MaterialSocket对象
     */
    private static ConcurrentHashMap<String, Map<String, MaterialSocket>> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 客户端ip
     */
    private String ip;

    /**
     * 用户标识
     */
    private String uKey;

    /**
     * 平台标识
     */
    private String platform;

    @OnOpen
    public void onOpen(Session session, @PathParam("uKey") String uKey , @PathParam("platform") String platform) throws IOException{
        this.session = session;
        this.uKey = uKey;
        this.platform = platform;
        this.ip = ip;
        if (webSocketMap.get(uKey) != null) {
            Map<String, MaterialSocket> materialSocketMap = webSocketMap.get(uKey);
            materialSocketMap.put(platform, this);
            webSocketMap.put(uKey, materialSocketMap);
        } else {
            Map<String, MaterialSocket> materialSocketMap = new HashMap<>();
            materialSocketMap.put(platform, this);
            webSocketMap.put(uKey, materialSocketMap);
        }
        System.out.println("用户：" + uKey + " 平台：" + platform + " 成功连接");
        // 在线连接数加1
        addOnlineCount();
        this.sendMessage("{\"code\":0,\"message\":\""+platform+"连接成功\",\"data\":{}}");
        if (platform.equals("mobile")) {
            this.sendMessage(webSocketMap.get(uKey).get("pc"), "{\"code\":0,\"message\":\"移动端已连接\",\"data\":{\"noticeKey\":\"mobile_connaction_success\"}}");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException{
        System.out.println("来自" + this.platform + "的消息:" + message);
        if (platform.equals("mobile")) {
            JSONObject jsonObject = JSON.parseObject(message);
            if (jsonObject.getJSONObject("data").getString("noticeKey").equals("mobile_upload_success")) {
                this.sendMessage(webSocketMap.get(uKey).get("pc"), message);
            }
        }
    }

    /**
     * 连接关闭时调用的方法
     * @param ip
     */
    @OnClose
    public void onClose(@PathParam("ip") String ip) throws IOException{
         webSocketMap.remove(ip);
//         Map<String, String> map = session.getPathParameters();
//         ip = map.get("ip");
//         webSocketMap.remove(map.get("ip"));
        subOnlineCount();
        System.out.println("客户端 ip:" + ip + " 已断开！");
        this.sendMessage("已断开");
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        System.out.println("发生错误");
        this.sendMessage("发生错误");
        error.printStackTrace();
    }

    /**
     * 在线连接加1
     */
    public static synchronized void addOnlineCount() {
        MaterialSocket.onlineCount ++;
    }

    /**
     * 在线连接减1
     */
    public static synchronized void subOnlineCount() {
        MaterialSocket.onlineCount --;
    }

    /**
     * 获取当前在线连接数
     * @return
     */
    public static synchronized int getOnlineCount() {
        return MaterialSocket.onlineCount;
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public void sendMessage(MaterialSocket materialSocket, String message) throws IOException {
        materialSocket.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     * */
//    public static void sendInfo(String message) throws IOException {
//        for (Map.Entry<String, MaterialSocket> entry : webSocketMap.entrySet()) {
//            MaterialSocket value = entry.getValue();
//            value.sendMessage(message);
//        }
//    }
}