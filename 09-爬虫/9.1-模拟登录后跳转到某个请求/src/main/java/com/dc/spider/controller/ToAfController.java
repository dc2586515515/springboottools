package com.dc.spider.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-15
 */
@Controller
public class ToAfController {

    /**
     * 测试登录 安防
     */
    @RequestMapping("/testlogin/login")
    public String testLogin(HttpServletResponse response) {
        //方法一：直接访问页面，页面登录表单，将用户名，密码写死，页面加载完成后，设置自动提交表单，将页面元素置为display:none
        return "test";
    }

}
