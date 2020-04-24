package com.namdq.serverjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerJDBCApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerJDBCApplication.class, args);
    }

}
