package com.iflytek.jbxie.learn2.common.rmq;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author jbxie
 * @create 2020/06/17 17:12
 */
@Log4j2
@Component
public class Consumer {
    /**
     * 消费者实体对象
     */
    private DefaultMQPushConsumer consumer;
    /**
     * 消费者组
     */
    public static final String CONSUMER_GROUP = "test_consumer";
    /**
     * 通过构造函数 实例化对象
     */
    public Consumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
//        consumer.setNamesrvAddr("127.12.15.6:9876;127.12.15.6:9877");
        consumer.setNamesrvAddr("172.31.236.129:9876");
        //消费模式:一个新的订阅组第一次启动从队列的最后位置开始消费 后续再启动接着上次消费的进度开始消费
        /**
         * 1. CONSUME_FROM_LAST_OFFSET：第一次启动从队列最后位置消费，后续再启动接着上次消费的进度开始消费
         2. CONSUME_FROM_FIRST_OFFSET：第一次启动从队列初始位置消费，后续再启动接着上次消费的进度开始消费
         3. CONSUME_FROM_TIMESTAMP：第一次启动从指定时间点位置消费，后续再启动接着上次消费的进度开始消费
         以上所说的第一次启动是指从来没有消费过的消费者，如果该消费者消费过，那么会在broker端记录该消费者的消费位置，如果该消费者挂了再启动，那么自动从上次消费的进度开始
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        //订阅主题和 标签（ * 代表所有标签)下信息
        consumer.subscribe("topic_family", "*");
        consumer.setPersistConsumerOffsetInterval(1000);
        // //注册消费的监听 并在此监听中消费信息，并返回消费的状态信息
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            // msgs中只收集同一个topic，同一个tag，并且key相同的message
            // 会把不同的消息分别放置到不同的队列中
            try {
                for (Message msg : msgs) {
                    //消费者获取消息 这里只输出 不做后面逻辑处理
                    String body = new String(msg.getBody(), "utf-8");
                    log.info("Consumer-获取消息-主题topic为={}, 消费消息为={}, {}", msg.getTopic(), body, msg.getProperties());
                }
            } catch (UnsupportedEncodingException e) {
                log.info("Consumer-获取消息 error");
                e.printStackTrace();
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
//                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeOrderlyStatus.SUCCESS;
//            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
//        consumer.start();
        System.out.println("消费者 启动成功=======");
    }
}
