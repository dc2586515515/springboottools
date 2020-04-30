package com.dc.optimisticLocking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dc.optimisticLocking.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-12-18
 */
public interface UserMapper extends BaseMapper<User> {
    int myUpdateById(@Param("vo") User user);
}