package com.iflytek.jbxie.learn2.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * mongodb配置信息
 *
 * @author jbxie
 * @create 2020/03/04 22:02
 */
//@Configuration
public class MongoConfig {
//    @Bean
    public MongoTemplate getMongoTemplate() {
        return  new MongoTemplate(new SimpleMongoDbFactory(new MongoClient("172.31.236.129", 27017), "jbxie"));
    }
}
