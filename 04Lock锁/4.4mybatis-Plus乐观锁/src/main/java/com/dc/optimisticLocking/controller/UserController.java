package com.dc.optimisticLocking.controller;


import com.dc.optimisticLocking.entity.User;
import com.dc.optimisticLocking.mapper.UserMapper;
import com.dc.optimisticLocking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;

    @Autowired
    private UserMapper userMapper;


    /**
     * 可以用jmeter 500个线程并发测试
     * 1. 去掉User实体的 @Version注解，发现控制台全部输出更新成功，执行了50个sql,而且数据库的version字段也没有自增
     * 2. 加上User实体的 @Version注解，发现控制台部分打印成功，部分失败，失败的一部分是去更新的时候，
     * 发现版本Version已经不等于更新前自己所读取的了，所以不会去执行更新的sql。
     * <p>
     * 3.也可以去MybatisplusdemoApplicationTests中测试
     */
    @RequestMapping("/lock")
    public void testOptmisticLock() {
        // 正确的操作流程
        // 1.根据ID获取某条数据
        User user = userMapper.selectById(1);

        System.out.println(user);
        // 2.修改这条数据
        user.setName("Bill Gates");
        user.setAge(user.getAge() + 1);
        // 3.根据ID修改这条数据（mybatis plus 的内层帮我们多加了一个where条件，保证乐观锁的实现） /
        // 无sql的乐观锁
        int num = userMapper.updateById(user);


        // 自己写sql 实现的 乐观锁
        // int num = userMapper.myUpdateById(user);

        if (num > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失败");
        }
    }
}
