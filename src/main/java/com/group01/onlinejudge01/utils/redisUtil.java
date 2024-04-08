package com.group01.onlinejudge01.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class redisUtil implements InitializingBean {

    @Value("${redis.url}")
    private String url;

    @Value("${redis.password}")
    private String password;

    public static String URL;
    public static String PASSWORD;

    @Override
    public void afterPropertiesSet() throws Exception {
        URL = url;
        PASSWORD = password;
    }
}
