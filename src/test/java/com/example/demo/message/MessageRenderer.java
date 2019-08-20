package com.example.demo.message;

/**
 * @program: LearnningDemo
 * @description:
 * @author: wangchong
 * @create: 2019-07-19 17:20
 **/
public interface MessageRenderer {
    void render();
    void setMessageProvider(MessageProvider messageProvider);
    MessageProvider getMessageProvider();
}
