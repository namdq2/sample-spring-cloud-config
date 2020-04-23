package com.namdq.client2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Value("${message:default}")
    private String message;

    @Value("${name:default}")
    private String name;

    @Value("${environment:default}")
    private String environment;

    @Value("${global:default}")
    private String global;

    @GetMapping("/message")
    public Message getMessage() {
        return new Message(message, name, environment, global);
    }
}
