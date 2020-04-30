package com.dc.optimisticLocking;


import com.dc.optimisticLocking.entity.User;
import com.dc.optimisticLocking.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptimisticLocking.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisplusdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //测试乐观锁
    @Test
    public void testOptmisticLock() {

        // 正确的操作流程
        // 1.根据ID获取某条数据
        User user = userMapper.selectById(1);
        System.out.println(user);
        // 2.修改这条数据
        user.setName("MaYun");
        user.setAge(55);
//		student.setVersion(student.getVersion()); // mybatis plus 会在老版本的version + 1 ，所以不需要再手动+1；

        try {
            System.out.println("开始休眠");
            // 可以延迟10000 ,手动去修改数据库这条数据的version 为其他数字 ，最后的结果就是无法更新成功
            Thread.sleep(10000);// Thread.sleep(10000);
            int num = userMapper.updateById(user); // // 3.根据ID修改这条数据（mybatis plus 的内层帮我们多加了一个where条件，保证乐观锁的实现） /
            System.out.println("休眠结束");
            if (num > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
            System.out.println(userMapper);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
