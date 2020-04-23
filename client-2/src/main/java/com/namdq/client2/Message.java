package com.namdq.client2;

public class Message {

    private String message;
    private String name;
    private String environment;
    private String global;

    public Message(String message, String name, String environment, String global) {
        this.message = message;
        this.name = name;
        this.environment = environment;
        this.global = global;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getGlobal() {
        return global;
    }

    public void setGlobal(String global) {
        this.global = global;
    }
}
