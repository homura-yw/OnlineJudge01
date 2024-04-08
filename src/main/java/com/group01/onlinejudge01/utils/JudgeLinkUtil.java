package com.group01.onlinejudge01.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JudgeLinkUtil implements InitializingBean {
    @Value("${onlinejudge.url}")
    private String url;

    public static String URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        URL = url;
    }
}
