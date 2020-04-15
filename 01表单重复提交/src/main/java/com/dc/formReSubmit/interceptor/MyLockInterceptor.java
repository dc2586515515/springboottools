package com.dc.formReSubmit.interceptor;

import com.dc.formReSubmit.lock.MyLock;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@Aspect
@Configuration
public class MyLockInterceptor {
    // 创建一个缓存，类似于HashMap这样的键值对。限制表单提交的相同的数据5秒内不可重复提交。
    private static final Cache<String, Object> CACHES =
            CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(5, TimeUnit.SECONDS).build();

    @Around(value = "execution(public * *(..)) && @annotation(com.dc.formReSubmit.lock.MyLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Exception {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // 获得key
        String key = generateKey(pjp, method);
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                System.out.println("重复提交");
                return "重复提交";
            } else {
                CACHES.put(key, key);
                System.out.println("提交成功");
            }
        }

        //返回主函数继续执行,这部不可或缺，否则无法返回主方法中
        try {
            Object[] args = pjp.getArgs();
            return pjp.proceed(args);
        } catch (Throwable throwable) {
            throw new Exception("服务器内部错误");
        }
    }


    /**
     * 生成key 类名+方法名+方法参数
     *
     * @param pjp
     * @param method
     * @return
     */
    public String generateKey(ProceedingJoinPoint pjp, Method method) {
        KeyGenerator keyGenerator = new SimpleKeyGenerator();
        MyLock myLock = method.getAnnotation(MyLock.class);
        return myLock.key() + keyGenerator.generate(pjp.getTarget(), method, pjp.getArgs());
    }

}
