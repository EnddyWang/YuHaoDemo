package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: LearnningDemo
 * @description:
 * @author: wangchong
 * @create: 2019-08-05 15:59
 **/
@RestController
@RequestMapping("/demo")
public class SpringDemo {

    @RequestMapping("/model")
    public void demoModel(){
        System.out.println("HELLO WORLD");
    }
}
