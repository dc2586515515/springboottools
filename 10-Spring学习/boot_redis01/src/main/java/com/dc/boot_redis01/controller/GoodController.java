package com.dc.boot_redis01.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-10
 */
@RestController
public class GoodController {

    public static final String REDIS_LOCK_KEY = "lockredis";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/buy_goods")
    public String buy_Goods() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();

        try {
            // Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value);
            // // 防止服务的机器宕机，无法执行finally的释放锁代码，需要在redis中给锁设定超时时间
            // stringRedisTemplate.expire(REDIS_LOCK_KEY, 10L, TimeUnit.SECONDS);

            // 加锁 与设置过期时间不能分为两行写，不具备原子性，假如加锁代码执行完毕，设置时间未执行的时候宕机了，则不具备原子性，所以合成一行
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value, 10L, TimeUnit.SECONDS);

            if (!flag) {
                return "抢锁失败，┭┮﹏┭┮";
            }
            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
        } finally {
            // 如果锁超时时间设置10s，A线程执行了12s，在第10s时，redis已经将A的锁删除，同时B线程获得锁，第12S时，A线程执行完毕，删除锁，但此时B还在执行，A删除的是B的锁，误删了别人的锁，因此，需要判断Value是否等于自己的锁的值，才能删除
            // if (stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY).equals(value)) {
            //     // 释放锁
            //     stringRedisTemplate.delete(REDIS_LOCK_KEY);
            // }

            // 上述 判断+del删除操作 不是原子性的，推荐使用lua脚本保证原子性，但是如果不让用lua脚本的话，可以用redis事务
            while (true) {
                stringRedisTemplate.watch(REDIS_LOCK_KEY); //加事务，乐观锁
                if (value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY))) {
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    stringRedisTemplate.multi();//开始事务
                    stringRedisTemplate.delete(REDIS_LOCK_KEY);
                    List<Object> list = stringRedisTemplate.exec();
                    if (list == null) {  //如果等于null，就是没有删掉，删除失败，再回去while循环那再重新执行删除
                        continue;
                    }
                }
                //如果删除成功，释放监控器，并且breank跳出当前循环
                stringRedisTemplate.unwatch();
                break;
            }
        }
    }

}
