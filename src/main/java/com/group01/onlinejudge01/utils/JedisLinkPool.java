package com.group01.onlinejudge01.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisLinkPool {
    private static final JedisPool jedisPool;

    static private String url = redisUtil.URL;

    static private String password = redisUtil.PASSWORD;

    static private Integer timeout = 1000;

    static private Integer port = 6379;

    static {
        //配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大连接
        poolConfig.setMaxTotal(16);
        //最大空闲连接
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(4);
        poolConfig.setMaxWait(Duration.ofSeconds(3));
        // 创建连接对象
        jedisPool = new JedisPool(
                poolConfig,
                url,
                port,
                timeout,
                password);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}