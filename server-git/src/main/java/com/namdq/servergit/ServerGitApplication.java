package com.namdq.servergit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerGitApplication.class, args);
    }

}
