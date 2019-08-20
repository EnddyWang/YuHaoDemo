package com.example.demo.factory;

import com.example.demo.message.MessageProvider;
import com.example.demo.message.MessageRenderer;

import java.util.Properties;

/**
 * @program: LearnningDemo
 * @description:
 * @author: wangchong
 * @create: 2019-07-19 17:24
 **/
public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    //静态代码块初始化工厂
    static {
        instance = new MessageSupportFactory();
    }

    private Properties properties;
    private MessageRenderer messageRenderer;
    private MessageProvider messageProvider;

    private MessageSupportFactory(){
        try {
            properties.load(this.getClass().getResourceAsStream("/application.properties"));
            String renderer = properties.getProperty("renderer.class");
            String provider = properties.getProperty("provider.class");
            messageRenderer = (MessageRenderer)Class.forName(renderer).newInstance();
            messageProvider = (MessageProvider)Class.forName(provider).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static MessageSupportFactory getInstance(){
        return instance;
    }

    public MessageRenderer getMessageRenderer(){
        return messageRenderer;
    }

    public MessageProvider getMessageProvider(){
        return messageProvider;
    }
}
