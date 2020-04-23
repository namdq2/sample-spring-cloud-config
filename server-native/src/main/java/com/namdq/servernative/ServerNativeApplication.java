package com.namdq.servernative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerNativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerNativeApplication.class, args);
    }

}
