package com.dc.RedisShareSession.test;

import com.dc.RedisShareSession.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description 可能会出现锁超时，多个线程同时获得锁，导致脏数据
 * @Author DC
 * @Date 2020-04-16
 */
@Component
public class RedisLockTest {
    private static Logger logger = LoggerFactory.getLogger(RedisLockTest.class);
    @Autowired
    RedisUtils redisUtils;

    String redisKey = "demo-RedisLockTest-isRun";

    //单位为秒  默认15s
    private long redis_default_expire_time = 15 * 1;

    //每隔30s执行一次
    @Scheduled(cron = "*/30 * * * * ?")
    public void init() throws InterruptedException {
        //-------------上分布式锁开始-----------------
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //获取本机ip
        String ip = addr.getHostAddress();

        //此key存放的值为任务执行的ip，
        boolean lock = redisUtils.tryLock(redisKey, ip, redis_default_expire_time);
        logger.info("============本次聚类定时任务开始==============");
        if (lock) {
            logger.info("============获得分布式锁成功=======================");
            //TODO 开始执行任务 执行结束后需要释放锁
            run();
            //释放锁
            redisUtils.del(redisKey);
            logger.info("============释放分布式锁成功=======================");

        } else {
            logger.info("============获得分布式锁失败=======================");
            ip = (String) redisUtils.get(redisKey);
            logger.info("============{}机器上占用分布式锁，聚类任务正在执行=======================", ip);
            logger.info("============本次聚类定时任务结束==============");

            // 本次执行任务已经有人继续执行，所以此线程不再执行，等到下次定时时间再去竞争锁
            return;
        }
    }

    public void run() throws InterruptedException {

        logger.info("执行中");
        // 执行10s
        Thread.sleep(1000 * 10);
        logger.info("执行结束");

    }
}
