package cn.itcast.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-28
 */
@SpringBootApplication
// @EnableScheduling//使用定时任务, 需要先开启定时任务, 需要添加注解
public class JdApplication {
    public static void main(String[] args) {
        SpringApplication.run(JdApplication.class, args);
    }
}
