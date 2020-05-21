package com.iflytek.jbxie.learn2.controller;

import com.iflytek.jbxie.learn2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author jbxie
 * @create 2020/03/04 17:09
 */
@Controller
public class TestMongoSetController {

//    @GetMapping(value = "/getUser")
//    public List<User> getUser() {
//        List<User> userList = mongoTemplate.findAll(User.class, "t");
//        return userList;
//    }
//
//    @GetMapping(value = "/addUser")
//    public String addUser() {
//        for (int i = 20; i < 25; i ++) {
//            User user = new User("zhangsan" + i, "男" + i , i);
//            mongoTemplate.insert(user, "t");
//        }
//        return "success";
//    }
}
