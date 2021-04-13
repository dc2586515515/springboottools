package com.dc.jucandjvm.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description
 * 1 故障现象
 *      java.util.ConcurrentModificationException   并发修改异常
 *
 * 2 导致原因
 *
 * 3 解决方案
 *      3.1 new Vector<>();  不推荐
 *      3.2 Collections.synchronizedList(new ArrayList<>());  不推荐
 *      3.3 new CopyOnWriteArrayList<>();
 *
 * 4 优化建议（同样的错误不犯2次）
 *
 * @Author DC
 * @Date 2021-04-13
 */
public class NotSafeDemo03 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();  // Collections.synchronizedList(new ArrayList<>()); // new Vector<>(); // new ArrayList<>();

        // list.add("a");
        // list.add("a");
        // list.add("a");
        // list.forEach(System.out::println);

        for (int i = 0; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}