package com.dc.lambda.test;

import java.util.function.Consumer;

/**
 * @Description 闭包问题
 * @Author DC
 * @Date 2020-05-27
 */
public class ClosePack {
    /**
     * 这个问题我们在匿名内部类中也会存在，如果我们把注释放开会报错，告诉我 num 值是 final 不能被改变。
     * 这里我们虽然没有标识 num 类型为 final，但是在编译期间虚拟机会帮我们加上 final 修饰关键字。
     */
    public static void main(String[] args) {
        int num = 10;

        Consumer<String> consumer = ele -> {
            System.out.println(num);
        };

        //num = num + 2;
        consumer.accept("hello");
    }
}
