package com.dc.RedisShareSession.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-16
 */
@Component
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 上锁
     * 将键值对设定一个指定的时间timeout.
     *
     * @param key
     * @param timeout 键值对缓存的时间，单位是秒
     * @return 设置成功返回true，否则返回false
     */
    public boolean tryLock(String key, Object value, long timeout) {
        //底层原理就是Redis的setnx方法
        boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (isSuccess) {
            //设置分布式锁的过期时间
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return isSuccess;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        Object obj = null;
        try {
            obj = key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("redis is unenabled.....");
            return null;
        }

        return obj;
    }

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
}
