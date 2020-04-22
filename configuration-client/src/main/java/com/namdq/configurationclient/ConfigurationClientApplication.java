package com.namdq.configurationclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigurationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationClientApplication.class, args);
    }

}

@RestController
class MessageController {

    @Value("${message:Hello namdq!}")
    private String message;

    @Value("${profile:default}")
    private String profile;

    @GetMapping("/message")
    public String getMessage() {
        return message + " --- " + "Profile: " + profile;
    }
}
