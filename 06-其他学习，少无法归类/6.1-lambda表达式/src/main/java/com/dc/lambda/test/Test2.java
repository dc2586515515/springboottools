package com.dc.lambda.test;

import com.dc.lambda.service.NoReturnMultiParam;
import com.dc.lambda.service.NoReturnNoParam;
import com.dc.lambda.service.NoReturnOneParam;
import com.dc.lambda.service.ReturnMultiParam;
import com.dc.lambda.service.ReturnOneParam;

/**
 * @Description
 * @Author DC
 * @Date 2020-05-26
 */
public class Test2 {
    public static void main(String[] args) {

        //1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
        NoReturnMultiParam lamdba1 = (a, b) -> {
            System.out.println("简化参数类型");
        };
        lamdba1.method(1, 2);

        //2.简化参数小括号，如果只有一个参数则可以省略参数小括号
        NoReturnOneParam lambda2 = a -> {
            System.out.println("简化参数小括号");
        };
        lambda2.method(1);

        //3.简化方法体大括号，如果方法条只有一条语句，则可以省略方法体大括号
        NoReturnNoParam lambda3 = () -> System.out.println("简化方法体大括号");
        lambda3.method();

        //4.如果方法体只有一条语句，并且是 return 语句，则可以省略方法体大括号
        ReturnOneParam lambda4 = a -> a + 3;
        System.out.println(lambda4.method(3));

        ReturnMultiParam lambda5 = (a, b) -> a + b;
        System.out.println(lambda5.method(3, 6));
    }
}
