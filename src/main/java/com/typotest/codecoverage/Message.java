package com.typotest.codecoverage;

public class Message {
    public String getMessage(String name) {
        StringBuilder sb = new StringBuilder();
        if (name == null || name.trim().length() == 0) {
            sb = sb.append("Missing Name");
        } else {
            sb = sb.append("Hello ").append(name);
        }
        return sb.toString();
    }

    public String echoMessage(String name){
        return name;
    }
}
