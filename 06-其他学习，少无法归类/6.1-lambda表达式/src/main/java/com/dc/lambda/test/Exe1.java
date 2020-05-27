package com.dc.lambda.test;

import com.dc.lambda.service.ReturnOneParam;

/**
 * @Description 利用 lambda表达式的接口快速指向一个已经被实现的方法。
 * @Author DC
 * @Date 2020-05-26
 */
public class Exe1 {
    /**
     * 方法归属者::方法名 静态方法的归属者为类名，普通方法归属者为对象
     */

    public static void main(String[] args) {
        ReturnOneParam lambda1 = a -> doubleNum(a);
        System.out.println(lambda1.method(3));


        //lambda2 引用了已经实现的 doubleNum 方法，静态方法的归属者为类名
        ReturnOneParam lambda2 = Exe1::doubleNum;
        System.out.println(lambda2.method(3));


        //lambda5 引用了已经实现的 addTwo 方法，普通方法归属者为对象
        Exe1 exe1 = new Exe1();
        ReturnOneParam lambda5 = exe1::addTwo;
        System.out.println(lambda5.method(3));

    }

    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */
    public static int doubleNum(int a) {
        return a * 2;
    }

    public int addTwo(int a) {
        return a + 3;
    }
}
