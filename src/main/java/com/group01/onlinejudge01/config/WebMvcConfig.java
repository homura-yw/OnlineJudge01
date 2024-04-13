//package com.group01.onlinejudge01.config;
//
//import com.group01.onlinejudge01.interceptor.LoginProtectInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginProtectInterceptor loginProtectInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginProtectInterceptor).addPathPatterns();
//    }
//}