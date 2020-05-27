package com.dc.lambda.test;

import com.dc.lambda.service.NoReturnMultiParam;
import com.dc.lambda.service.NoReturnNoParam;
import com.dc.lambda.service.NoReturnOneParam;
import com.dc.lambda.service.ReturnMultiParam;
import com.dc.lambda.service.ReturnNoParam;
import com.dc.lambda.service.ReturnOneParam;


/**
 * Lambda简介:
 * <p>
 * Lambda 表达式是 JDK8 的一个新特性，可以取代大部分的匿名内部类，写出更优雅的 Java 代码，
 * 尤其在集合的遍历和其他集合操作中，可以极大地优化代码结构。
 * <p>
 * JDK 也提供了大量的内置函数式接口供我们使用，使得 Lambda 表达式的运用更加方便、高效。
 * <p>
 * 对接口的要求
 * <p>
 * 虽然使用 Lambda 表达式可以对某些接口进行简单的实现，但并不是所有的接口都可以使用 Lambda 表达式来实现。
 * Lambda 规定接口中只能有一个需要被实现的方法，不是规定接口中只能有一个方法
 * <p>
 * jdk 8 中有另一个新特性：default， 被 default 修饰的方法会有默认实现，不是必须被实现的方法，所以不影响 Lambda 表达式的使用。
 *
 * @FunctionalInterface 修饰函数式接口的，要求接口中的抽象方法只有一个。这个注解往往会和 lambda 表达式一起出现。
 * <p>
 * Lambda 基础语法
 * <p>
 * 我们这里给出六个接口，后文的全部操作都利用这六个接口来进行阐述。
 */

/**
 * @Description
 * @Author DC
 * @Date 2020-05-26
 */
public class Test1 {
    public static void main(String[] args) {

        // //无参无返回
        NoReturnNoParam noReturnNoParam = () -> {
            System.out.println("无参无返回-NoReturnNoParam");
        };
        noReturnNoParam.method();

        //一个参数无返回
        NoReturnOneParam noReturnOneParam = (int a) -> {
            System.out.println("一个参数无返回-NoReturnOneParam 参数为：" + a);
        };
        noReturnOneParam.method(6);


        //多个参数无返回
        NoReturnMultiParam noReturnMultiParam = (int a, int b) -> {
            System.out.println("多个参数无返回-NoReturnMultiParam param:" + "{" + a + "," + +b + "}");
        };
        noReturnMultiParam.method(7, 8);

        //无参有返回值
        ReturnNoParam returnNoParam = () -> {
            System.out.println("ReturnNoParam");
            return 8;
        };
        int res = returnNoParam.method();
        System.out.println("无参有返回值return：" + res);

        //一个参数有返回值
        ReturnOneParam returnOneParam = (int a) -> {
            System.out.println("一个参数有返回值param：" + a);
            return a;
        };
        int res2 = returnOneParam.method(6);
        System.out.println("return:" + res2);


        // //多个参数有返回值
        ReturnMultiParam returnMultiParam = (int a, int b) -> {
            System.out.println("ReturnMultiParam param:" + "{" + a + "," + b + "}");
            return a + b;
        };
        int res3 = returnMultiParam.method(8, 9);
        System.out.println("return:" + res3);
    }
}
