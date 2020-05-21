package com.iflytek.jbxie.learn2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@EnableMongoRepositories
public class Learn2Application {

	public static void main(String[] args) {
		SpringApplication.run(Learn2Application.class, args);
	}

}
