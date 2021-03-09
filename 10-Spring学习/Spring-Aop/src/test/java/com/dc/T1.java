package com.dc;

import com.dc.service.CalcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class T1 {
    @Autowired
    private CalcService calcService;

    @Test
    public void testSpring4() {
        System.out.println("spring版本：" + SpringVersion.getVersion() + "\t" + "SpringBoot版本：" + SpringBootVersion.getVersion());
        System.out.println();
        // calcService.div(10, 2);
        calcService.div(10, 0);
    }
}
