package com.namdq.client2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {
//    @Value("${message:default}")
//    private String message;

    private static String message;//MESSAGE_STATIC;

    @Value("${name:default}")
    private String name;

    @Value("${environment:default}")
    private String environment;

    @Value("${global:default}")
    private String global;

    @GetMapping("/message")
    public Message getMessage() {
        String msg = message;
        System.out.println(msg);
        System.out.println("Sleep");
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg = message;
        System.out.println(msg);
        return new Message(message, name, environment, global);
    }

    @Value("${message}")
    public void setMessageStatic(String message){
        MessageController.message = message;
    }

}
