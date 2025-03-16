package com.community.controller;

import com.community.pojo.RegisterInfo;
import com.community.pojo.Result;
import com.community.pojo.User;
import com.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()){
            log.info("注册失败，用户名或密码为空：{}", user);
            return Result.error("用户名或密码不能为空！");
        }
        Map<String, Long> info = userService.register(user);
        if (info != null){
            log.info("注册用户：{}", user);
            return Result.success(info);
        }
        log.info("注册失败，用户已存在：{}", user);
        return Result.error("注册失败！用户名已存在");
    }
}
