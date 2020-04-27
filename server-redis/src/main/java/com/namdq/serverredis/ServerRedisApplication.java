package com.namdq.serverredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRedisApplication.class, args);
    }

}
