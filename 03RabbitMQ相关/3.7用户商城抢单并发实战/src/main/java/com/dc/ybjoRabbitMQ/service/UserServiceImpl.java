package com.dc.ybjoRabbitMQ.service;

import com.dc.ybjoRabbitMQ.dao.UserDao;
import com.dc.ybjoRabbitMQ.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jay.zhou
 * @date 2019/4/25
 * @time 8:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity selectByPrimaryKey(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public Boolean insert(UserEntity userEntity) {

        return userDao.insert(userEntity);
    }
}
