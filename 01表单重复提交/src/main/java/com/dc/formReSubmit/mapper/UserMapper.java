package com.dc.formReSubmit.mapper;

import com.dc.formReSubmit.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
public interface UserMapper {
    void addUser(@Param("vo") User user);
}
