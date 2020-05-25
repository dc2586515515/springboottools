package com.dc.chatroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dc.chatroom.dao.UserDao;
import com.dc.chatroom.vo.User;

import java.util.List;

/**
 * @BelongsProject: websocket
 * @BelongsPackage: com.dc.chatroom.service.impl
 * @Author: WonderHeng
 * @CreateTime: 2019-01-04 17:43
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //登录
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    //注册
    public void save(User user1) {

        userDao.save(user1);
    }

    //注册时差询名字是否重复
    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }




}
