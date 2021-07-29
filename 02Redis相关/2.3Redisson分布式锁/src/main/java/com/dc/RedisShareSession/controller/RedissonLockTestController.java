package com.dc.RedisShareSession.controller;

import com.dc.RedisShareSession.util.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class RedissonLockTestController {


    /**
     * 如果redisson 做分布式锁，解锁操作时，key在redis中是不存在的，
     * 会抛出异常
     */
    @GetMapping("/redissonLockTest")
    public void redissonLockTest() {
        String lockKey = "lock-test";
        String lockKey2 = "lock-test22";
        try {
            // 加锁，设置超时时间2s
            RedisLockUtil.lock(lockKey, 20, TimeUnit.SECONDS);
            // RedisLockUtil.lock(lockKey, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 释放锁
            RedisLockUtil.unlock(lockKey2);
            System.out.println("111111111");
        }
    }
}
