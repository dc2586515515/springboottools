package com.dc.jucandjvm.juc;

/**
 * @FunctionalInterface 函数式接口注解，接口中只有一个接口方法，如果超出一个方法，则报错，
 * 如果只有一个方法，但是没有加@FunctionalInterface注解，Java8底层会默认给我们加上
 */
@FunctionalInterface
interface Foo {
    // public void sayHello();

    public int add(int x, int y);

    /**
     *  default ， java8 interface接口中default修饰的方法可以有方法体,一个接口中default修饰的方法可以有多个
     * @param x
     * @param y
     * @return
     */
    default int mul(int x, int y){
        return x * y;
    }

    /**
     *  static静态方法，可以有方法体，一个接口中可以有多个 static修饰的方法
     * @param x
     * @param y
     * @return
     */
    public static int div(int x, int y){
        return x / y;
    }



}

/**
 * @Description
 * 只有函数式接口才能用lambdaa表达式
 * 1. 拷贝小括号， 写死右箭头 ， 落地大括号
 * 2. @FunctionalInterface
 * 3. default  （java8 interface中default修饰的方法可以有方法体,一个接口中default修饰的方法可以有多个）
 * 4. static静态方法，可以有方法体，一个接口中可以有多个 static修饰的方法
 *
 * @Author DC
 * @Date 2021-04-13
 */
public class LambdaExpressDemo02 {
    public static void main(String[] args) {

        // 1. 匿名内部类
    /*    Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("******Hello******");
            }

            @Override
            public int add(int x, int y) {
                return 0;
            }
        };
        foo.sayHello();*/

        // 2. Lambda  拷贝小括号， 写死右箭头 ， 落地大括号
        // （只能有一个方法，一个班长，不需要名字，大家都知道是他）
        /*Foo foo = () -> { System.out.println("******Hello  Lambda Express ******"); };
        foo.sayHello();*/

        Foo foo = (int x, int y) -> {
            System.out.println("com in add method");
            return x + y;
        };
        System.out.println(foo.add(3, 5));

        System.out.println(foo.mul(3, 5));

        System.out.println(Foo.div(10, 2));
    }
}
