package com.dc.RedisShareSession.test;

import com.dc.RedisShareSession.util.RedisUtils;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * @Description 解決锁超时
 * @Author DC
 * @Date 2020-04-16
 */
@Component
public class RedisLockTest02 {
    private static Logger logger = LoggerFactory.getLogger(RedisLockTest02.class);
    private static final Long POSTPONE_SUCCESS = 1L;
    private static final Long RELEASE_SUCCESS = 1L;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";

    // 解锁脚本(lua)
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


    // 延时脚本。lua脚本 ，判断当前key是否还持有锁，若持有，则延时锁过期时间，让其继续持有锁，否则返回0
    private static final String POSTPONE_LOCK_SCRIPT =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1], ARGV[2]) else return '0' end";

    // 获得锁得值
    private static final String GET_VALUE_BYKEY =
            "return redis.call('get', KEYS[1])";


    @Autowired
    private RedisUtils redisUtils;

    @Resource
    private RedisTemplate<Serializable, Object> redisTemplate;

    // 锁key
    String redisKey = "demo-RedisLockTest-isRun";

    //单位为秒  默认15s
    private long redis_default_expire_time = 15 * 1;
    // 获取本机ip地址作为valua
    InetAddress addr;

    {
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    //获取本机ip
    String ip = addr.getHostAddress();


    //每隔30s执行一次
    @Scheduled(cron = "*/30 * * * * ?")
    public void init() throws InterruptedException {
        //-------------上分布式锁开始-----------------
        InetAddress addr = null;

        //此key存放的值为任务执行的ip，  reditTemplate保存键值对会将key value都进行系列化，所以lua脚本是获取不到的，弃之,用jedis加锁
        // boolean lock = redisUtils.tryLock(redisKey, ip, redis_default_expire_time);

        // 加锁
        Boolean lock = redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            String result = jedis.set(redisKey, ip, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, redis_default_expire_time);
            if (LOCK_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });

        if (lock) {
            logger.info("============获得分布式锁成功，开始执行定时任务开始=======================");
            // 加锁成功, 启动一个延时线程, 防止业务逻辑未执行完毕就因锁超时而使锁释放
            PostponeTask postponeTask = new PostponeTask(redisKey, ip, redis_default_expire_time, this);
            Thread thread = new Thread(postponeTask);
            // 设置守护线程
            thread.setDaemon(Boolean.TRUE);
            thread.start();
            // 执行定时任务
            run();
            //释放锁，这里的释放锁，也不能用redistemplate的del了，这里的key会被序列化的，弃之，用下面执行lus脚本进行解锁
            // redisUtils.del(redisKey);
            unLock(redisKey, ip);
            logger.info("============释放分布式锁成功=======================");

        } else {
            logger.info("============获得分布式锁失败=======================");
            //todo 从redis中获取value,这种方式获取不到，，会自动序列化key
            // ip = (String) redisUtils.get(redisKey);
            // logger.info("============{}机器上占用分布式锁，聚类任务正在执行=======================", ip);

            logger.info("============其他机器上占用分布式锁，聚类任务正在其他机器上执行=======================");
            logger.info("============本次定时任务结束==============");

            // 本次执行任务已经有人继续执行，所以此线程不再执行，等到下次定时时间再去竞争锁
            return;
        }
    }


    /**
     * 锁延时
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public Boolean postpone(String key, String value, long expireTime) {
        return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            ArrayList<String> keys = Lists.newArrayList(key);
            ArrayList<String> args = Lists.newArrayList(value, String.valueOf(expireTime));
            Object result = jedis.eval(POSTPONE_LOCK_SCRIPT, keys, args);
            if (POSTPONE_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean unLock(String key, String value) {
        return redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(key), Collections.singletonList(value));
            if (RELEASE_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });
    }


    /**
     * 执行定时任务具体实现
     *
     * @throws InterruptedException
     */
    public void run() throws InterruptedException {

        logger.info("============定时任务执行中============");
        // 执行10s，此时任意时刻最多只有一个线程获得锁，只有一个线程在执行定时任务
        // Thread.sleep(1000 * 10);
        // 设置执行50s定时任务时候，就会出现超时问题，但是我们设置了守护线程延时锁的超时时间，就不会出现两个线程同时执行的问题
        Thread.sleep(1000 * 50);
        logger.info("============执行结束============");

    }


    /**
     * 首先先生成一个内部类去实现Runnable，作为守护线程的参数
     */
    public class PostponeTask implements Runnable {

        private String key;
        private String value;
        private long expireTime;
        private boolean isRunning;
        private RedisLockTest02 distributedLock;

        public PostponeTask() {
        }

        public PostponeTask(String key, String value, long expireTime, RedisLockTest02 distributedLock) {
            this.key = key;
            this.value = value;
            this.expireTime = expireTime;
            this.isRunning = Boolean.TRUE;
            this.distributedLock = distributedLock;
        }

        @Override
        public void run() {
            long waitTime = expireTime * 1000 * 2 / 3;// 线程等待多长时间后执行
            while (isRunning) {
                try {
                    Thread.sleep(waitTime);
                    if (distributedLock.postpone(key, value, expireTime)) {
                        logger.info("...................................延时成功...................................");
                    } else {
                        this.stop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void stop() {
            this.isRunning = Boolean.FALSE;
        }

    }
}
