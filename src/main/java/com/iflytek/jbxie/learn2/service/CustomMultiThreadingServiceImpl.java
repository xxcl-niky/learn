package com.iflytek.jbxie.learn2.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 自定义多线程服务
 *
 * @author jbxie
 * @create 2020/02/12 18:50
 */
@Log4j2
@Service
public class CustomMultiThreadingServiceImpl implements CustomMultiThreadingService {
    @Override
    @Async
    public void executeAsyncTask1(int i) {
//        log.info("CustomMultiThreadingServiceImpl ==> executeAsyncTask1 method: 执行异步任务{} ", i);
    }

    @Override
    @Async
    public void executeAsyncTask2(int i) {
//        log.info("CustomMultiThreadingServiceImpl ==> executeAsyncTask2 method: 执行异步任务{} ", i);
    }
}
