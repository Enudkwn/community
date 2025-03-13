package com.community.controller;

import com.community.pojo.LoginInfo;
import com.community.pojo.Result;
import com.community.pojo.User;
import com.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("登录：{}", user);
        LoginInfo info = userService.login(user);
        if (info != null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误！");
    }

}
