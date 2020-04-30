package com.dc.optimisticLocking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dc.optimisticLocking.entity.User;
import com.dc.optimisticLocking.mapper.UserMapper;
import com.dc.optimisticLocking.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-12-18
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
