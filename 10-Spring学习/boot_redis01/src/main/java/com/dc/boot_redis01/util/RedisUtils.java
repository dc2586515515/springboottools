package com.dc.boot_redis01.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-10
 */
public class RedisUtils {
    private static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);

        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 100000);
    }

    public static Jedis getJedis() throws Exception {
        if (null != jedisPool) {
            return jedisPool.getResource();
        }
        throw new Exception("Jedispool is not ok");
    }
}
