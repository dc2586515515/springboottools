package com.dc.spring.demo.reflection;

import com.dc.spring.demo.vo.Student;

/**
 * @Description Java反射
 * @Author DC
 * @Date 2021-03-30
 */
public class Reflection {
    //1.通过new对象实现反射机制
    // 2.通过路径实现反射机制
    // 3.通过类名实现反射机制

    public static void main(String[] args) throws ClassNotFoundException {
        //方式一(通过建立对象)
        Student stu = new Student();
        Class<? extends Student> classobj1 = stu.getClass();
        System.out.println(classobj1.getName());

        //方式二（所在通过路径-相对路径）
        Class<?> classobj2 = Class.forName("com.dc.spring.demo.vo.Student");
        System.out.println(classobj2.getName());

        //方式三（通过类名）
        Class<Student> classobj3 = Student.class;
        System.out.println(classobj3.getName());

    }
}
