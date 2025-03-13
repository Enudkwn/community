package com.community.controller;

import com.community.pojo.RegisterInfo;
import com.community.pojo.Result;
import com.community.pojo.User;
import com.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("注册用户：{}", user);
        Map<String, Long> info = userService.register(user);
        if (info != null){
            return Result.success(info);
        }
        return Result.error("注册失败！用户名已存在");
    }
}
