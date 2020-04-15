package com.dc.formReSubmit.Controller;

import com.dc.formReSubmit.lock.MyLock;
import com.dc.formReSubmit.pojo.User;
import com.dc.formReSubmit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author DC
 * @Date 2020-04-15
 */
@RestController
public class FormReSubmitController {
    @Autowired
    private UserService userService;

    @GetMapping("/formLock")
    @MyLock(key = "FormReSubmitController.FormLock")  //注释该注解，浏览器快速点击http://localhost:8080/forReSubmit/formLock?userName=%E5%BC%A0%E4%B8%89&password=123  则会出现重复提交表单
    public String FormLock(User user) {
        userService.addUser(user);
        return null;
    }
}
