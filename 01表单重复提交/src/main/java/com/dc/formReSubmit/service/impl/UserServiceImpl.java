package com.dc.formReSubmit.service.impl;

import com.dc.formReSubmit.mapper.UserMapper;
import com.dc.formReSubmit.pojo.User;
import com.dc.formReSubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
