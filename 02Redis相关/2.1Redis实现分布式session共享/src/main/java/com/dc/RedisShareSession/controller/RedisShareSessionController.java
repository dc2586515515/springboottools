package com.dc.RedisShareSession.controller;

import com.dc.RedisShareSession.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@RestController
public class RedisShareSessionController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "index message";
        }
        return "please login first";
    }

    /**
     * 登录
     *
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username, String password) {
        User userFromDB = User.defaultUser();
        if (username.equals(userFromDB.getUsername())) {
            if (password.equals(userFromDB.getPassword())) {
                request.getSession().setAttribute("user", userFromDB);
                return "login success";
            }
        }
        return "login failure";
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String login(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "has already logout";
    }
}
