package com.iflytek.jbxie.learn2.controller;

import com.iflytek.jbxie.learn2.service.CustomMultiThreadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义多线程控制器
 *
 * @author jbxie
 * @create 2020/02/12 18:55
 */
@RestController
public class CustomMultiThreadingController {
    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    @ResponseBody
    @GetMapping(value = "/doTask")
    public String doTask() {
        for (int i = 0; i < 10; i++) {
            customMultiThreadingService.executeAsyncTask1(i);
            customMultiThreadingService.executeAsyncTask2(i);
        }
        return "success";
    }
}
