package com.iflytek.jbxie.learn2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author jbxie
 * @create 2020/04/09 14:11
 */
@RestController
public class TestController {
    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }
}
