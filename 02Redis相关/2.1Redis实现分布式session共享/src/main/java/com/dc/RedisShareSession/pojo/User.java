package com.dc.RedisShareSession.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;

    public static final User defaultUser() {
        return builder().id(1L).username("jay").password("3333").build();
    }

}
