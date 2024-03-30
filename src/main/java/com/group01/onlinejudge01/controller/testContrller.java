package com.group01.onlinejudge01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testContrller {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!! 114514！！";
    }
}
