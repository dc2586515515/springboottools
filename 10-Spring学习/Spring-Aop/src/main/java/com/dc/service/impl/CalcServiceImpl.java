package com.dc.service.impl;

import com.dc.service.CalcService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-09
 */
@Service
public class CalcServiceImpl implements CalcService {
    @Override
    public int div(int x, int y) {
        int result = x / y;
        System.out.println("CalcServiceImpl被调用，计算结果===>" + result);
        return result;
    }
}
