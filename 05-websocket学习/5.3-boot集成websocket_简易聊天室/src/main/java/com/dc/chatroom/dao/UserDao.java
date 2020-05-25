package com.dc.chatroom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dc.chatroom.vo.User;

import java.util.List;

/**
 * @BelongsProject: websocket
 * @BelongsPackage: com.dc.chatroom.dao
 * @Author: WonderHeng
 * @CreateTime: 2019-01-04 15:19
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    List<User> findByUsername(String username);
}
