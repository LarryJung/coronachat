package com.larry.reactivechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class ReactiveChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveChatApplication.class, args);
    }

}
